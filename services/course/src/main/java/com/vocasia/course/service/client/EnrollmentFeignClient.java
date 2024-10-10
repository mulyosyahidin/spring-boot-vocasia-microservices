package com.vocasia.course.service.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "enrollment")
public interface EnrollmentFeignClient {



}
