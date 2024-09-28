package com.vocasia.instructor.mapper;

import com.vocasia.instructor.entity.Instructor;

import java.util.HashMap;
import java.util.Map;

public class InstructorMapper {

    public static Map<String, Object> mapUserToResponse(Instructor instructor) {
        Map<String, Object> instructorData = new HashMap<>();

        instructorData.put("id", instructor.getId());
        instructorData.put("user_id", instructor.getUserId());
        instructorData.put("status", instructor.getStatus());
        instructorData.put("phone_number", instructor.getPhoneNumber());
        instructorData.put("summary", instructor.getSummary());
        instructorData.put("deleted_at", instructor.getDeletedAt());

        return instructorData;
    }

}
