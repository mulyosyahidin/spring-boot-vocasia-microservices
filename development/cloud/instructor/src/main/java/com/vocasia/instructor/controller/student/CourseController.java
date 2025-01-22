package com.vocasia.instructor.controller.student;

import com.vocasia.instructor.dto.ResponseDto;
import com.vocasia.instructor.entity.InstructorStudent;
import com.vocasia.instructor.entity.InstructorStudentCourse;
import com.vocasia.instructor.mapper.InstructorStudentCourseMapper;
import com.vocasia.instructor.request.AssignCourseToStudentInstructorRequest;
import com.vocasia.instructor.request.RegisterStudentRequest;
import com.vocasia.instructor.service.IInstructorStudentCourseService;
import com.vocasia.instructor.service.IInstructorStudentService;
import jakarta.validation.Valid;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/student")
public class CourseController {

    private final Logger logger = LoggerFactory.getLogger(CourseController.class);

    private final IInstructorStudentService instructorStudentService;
    private final IInstructorStudentCourseService instructorStudentCourseService;
    public CourseController(IInstructorStudentService iInstructorStudentService, IInstructorStudentCourseService iInstructorStudentCourseService) {
        this.instructorStudentService = iInstructorStudentService;
        this.instructorStudentCourseService = iInstructorStudentCourseService;
    }

    @PostMapping("/assign-courses")
    public ResponseEntity<ResponseDto> assignCourses(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                    @Valid @RequestBody AssignCourseToStudentInstructorRequest assignCourseToStudentInstructorRequest) {
        logger.info("CourseController.assignCourses called");

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

}
