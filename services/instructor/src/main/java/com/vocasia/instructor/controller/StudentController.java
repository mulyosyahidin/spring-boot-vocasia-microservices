package com.vocasia.instructor.controller;

import com.vocasia.instructor.dto.ResponseDto;
import com.vocasia.instructor.dto.client.authentication.UserDto;
import com.vocasia.instructor.dto.client.course.CourseDto;
import com.vocasia.instructor.dto.client.order.OrderDto;
import com.vocasia.instructor.dto.client.order.OrderItemDto;
import com.vocasia.instructor.entity.InstructorStudent;
import com.vocasia.instructor.entity.InstructorStudentCourse;
import com.vocasia.instructor.exception.CustomFeignException;
import com.vocasia.instructor.mapper.InstructorStudentCourseMapper;
import com.vocasia.instructor.mapper.InstructorStudentMapper;
import com.vocasia.instructor.request.AssignCourseToStudentInstructorRequest;
import com.vocasia.instructor.request.RegisterStudentRequest;
import com.vocasia.instructor.service.*;
import jakarta.validation.Valid;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StudentController {

    private final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final IInstructorStudentService instructorStudentService;
    private final IInstructorStudentCourseService instructorStudentCourseService;
    private final IAuthenticationService authenticationService;
    private final ICourseService courseService;
    private final IOrderService orderService;

    public StudentController(IInstructorStudentService iInstructorStudentService, IInstructorStudentCourseService iInstructorStudentCourseService,
                             IAuthenticationService iAuthenticationService, ICourseService iCourseService, IOrderService iOrderService) {
        this.instructorStudentService = iInstructorStudentService;
        this.instructorStudentCourseService = iInstructorStudentCourseService;
        this.authenticationService = iAuthenticationService;
        this.courseService = iCourseService;
        this.orderService = iOrderService;
    }

    @PostMapping("/assign-course")
    public ResponseEntity<ResponseDto> assignCourse(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                    @Valid @RequestBody AssignCourseToStudentInstructorRequest assignCourseToStudentInstructorRequest) {
        logger.info("StudentController.assignCourse called");

        RegisterStudentRequest registerStudentRequest = new RegisterStudentRequest();
        registerStudentRequest.setInstructorId(assignCourseToStudentInstructorRequest.getInstructorId());
        registerStudentRequest.setUserId(assignCourseToStudentInstructorRequest.getUserId());

        boolean isUserHasRegisteredAsInstructorStudent = instructorStudentService.isStudentRegistered(registerStudentRequest);
        InstructorStudent instructorStudent;

        if (!isUserHasRegisteredAsInstructorStudent) {
            instructorStudent = instructorStudentService.registerStudent(registerStudentRequest);
        } else {
            instructorStudent = instructorStudentService.findStudentByUserId(registerStudentRequest.getInstructorId(), registerStudentRequest.getUserId());
        }

        InstructorStudentCourse assignedCourse = instructorStudentCourseService.assignNewCourse(instructorStudent, assignCourseToStudentInstructorRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("instructor_student_course", InstructorStudentCourseMapper.mapToDto(assignedCourse));

        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(new ResponseDto(true, "Berhasil menambahkan kursus ke riwayat siswa", response, null));
    }

    @GetMapping("/students")
    public ResponseEntity<ResponseDto> getAllMyStudents(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                        @RequestHeader("X-INSTRUCTOR-ID") Long instructorId) {
        logger.info("StudentController.getAllMyStudents called");

        List<InstructorStudent> instructorStudents = instructorStudentService.findAllByInstructorId(instructorId);

        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> studentsData = new ArrayList<>();

        for (InstructorStudent instructorStudent : instructorStudents) {
            Map<String, Object> studentData = new HashMap<>();

            List<InstructorStudentCourse> studentCourses = instructorStudentCourseService.getStudentCourses(instructorStudent);

            studentData.put("instructor_student", InstructorStudentMapper.mapToDto(instructorStudent));
            studentData.put("course_count", studentCourses.size());

            try {
                UserDto userDto = authenticationService.findUserById(instructorStudent.getUserId(), correlationId);
                studentData.put("user", userDto);
            } catch (CustomFeignException e) {
                logger.error(e.getMessage(), e);

                return ResponseEntity
                        .status(e.getHttpStatus())
                        .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
            } catch (Exception e) {
                logger.error(e.getMessage(), e);

                return ResponseEntity
                        .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                        .body(new ResponseDto(false, e.getMessage(), null, null));
            }

            studentsData.add(studentData);
        }

        response.put("students", studentsData);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan daftar siswa", response, null));
    }

    @GetMapping("/students/{instructorStudentId}")
    public ResponseEntity<ResponseDto> getStudentById(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                      @PathVariable("instructorStudentId") Long instructorStudentId) {
        logger.info("StudentController.getStudentById called");

        InstructorStudent instructorStudent = instructorStudentService.findById(instructorStudentId);

        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> coursesData = new ArrayList<>();

        List<InstructorStudentCourse> studentCourses = instructorStudentCourseService.getStudentCourses(instructorStudent);

        response.put("instructor_student", InstructorStudentMapper.mapToDto(instructorStudent));
        response.put("course_count", studentCourses.size());

        try {
            UserDto userDto = authenticationService.findUserById(instructorStudent.getUserId(), correlationId);
            response.put("user", userDto);

            for (InstructorStudentCourse instructorStudentCourse : studentCourses) {
                Map<String, Object> courseData = new HashMap<>();

                courseData.put("instructor_student_course", InstructorStudentCourseMapper.mapToDto(instructorStudentCourse));

                CourseDto courseDto = courseService.findById(instructorStudentCourse.getCourseId(), correlationId);
                courseData.put("course", courseDto);

                OrderDto orderDto = orderService.findById(instructorStudentCourse.getOrderId(), correlationId);
                courseData.put("order", orderDto);

                OrderItemDto orderItemDto = orderService.findOrderItemById(instructorStudentCourse.getOrderId(), instructorStudentCourse.getCourseId(), correlationId);
                courseData.put("order_item", orderItemDto);

                coursesData.add(courseData);
            }

            response.put("courses", coursesData);
        } catch (CustomFeignException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(e.getHttpStatus())
                    .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, e.getMessage(), null, null));
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data siswa", response, null));
    }

}
