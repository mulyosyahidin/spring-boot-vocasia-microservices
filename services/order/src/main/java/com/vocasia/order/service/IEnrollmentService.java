package com.vocasia.order.service;

import com.vocasia.order.dto.client.EnrollmentDto;
import com.vocasia.order.request.client.EnrollNewCourseRequest;

import java.util.List;

public interface IEnrollmentService {

    List<EnrollmentDto> enrollNewCourse(EnrollNewCourseRequest enrollNewCourseRequest);

}
