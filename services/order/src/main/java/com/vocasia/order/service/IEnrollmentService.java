package com.vocasia.order.service;

import com.vocasia.order.request.client.EnrollNewCourseRequest;

public interface IEnrollmentService {

    void enrollNewCourse(EnrollNewCourseRequest enrollNewCourseRequest, String correlationId);

}
