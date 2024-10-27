package com.vocasia.order.service;

import com.vocasia.order.request.client.enrollment.EnrollNewCourseRequest;

public interface IEnrollmentService {

    void enrollNewCourse(EnrollNewCourseRequest enrollNewCourseRequest, String correlationId);

}
