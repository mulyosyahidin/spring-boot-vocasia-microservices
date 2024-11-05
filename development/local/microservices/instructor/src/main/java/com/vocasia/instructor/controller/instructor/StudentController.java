package com.vocasia.instructor.controller.instructor;

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
import com.vocasia.instructor.service.*;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/instructor")
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

    @GetMapping("/students")
    public ResponseEntity<ResponseDto> getAllMyStudents(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                        @RequestHeader("X-INSTRUCTOR-ID") Long instructorId,
                                                        @RequestParam(defaultValue = "1") int page) {
        logger.info("StudentController.getAllMyStudents called");

        page = page < 1 ? 1 : page - 1;
        int limit = 10;

        Pageable paging = PageRequest.of(page, limit);

        Page<InstructorStudent> instructorStudents = instructorStudentService.findAllByInstructorId(instructorId, paging);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();

        List<Map<String, Object>> instructorStudentsData;

        try {
            instructorStudentsData = instructorStudents.getContent().stream().map(instructorStudent -> {
                Map<String, Object> instructorStudentData = new HashMap<>();

                List<InstructorStudentCourse> studentCourses = instructorStudentCourseService.getStudentCourses(instructorStudent);
                UserDto userDto = authenticationService.findUserById(instructorStudent.getUserId(), correlationId);

                instructorStudentData.put("instructor_student", InstructorStudentMapper.mapToDto(instructorStudent));
                instructorStudentData.put("course_count", studentCourses.size());
                instructorStudentData.put("user", userDto);

                return instructorStudentData;
            }).toList();
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

        pagination.put("total_page", instructorStudents.getTotalPages());
        pagination.put("per_page", instructorStudents.getSize());
        pagination.put("current_page", instructorStudents.getNumber() + 1);
        pagination.put("total_items", instructorStudents.getTotalElements());

        response.put("data", instructorStudentsData);
        response.put("pagination", pagination);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan daftar siswa", response, null));
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<ResponseDto> getStudentById(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                      @PathVariable("id") Long id) {
        logger.info("StudentController.getStudentById called");

        InstructorStudent instructorStudent = instructorStudentService.findById(id);

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
