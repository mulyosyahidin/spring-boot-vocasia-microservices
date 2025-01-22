package com.vocasia.order.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vocasia.order.dto.ResponseDto;
import com.vocasia.order.dto.client.course.CourseDto;
import com.vocasia.order.dto.client.finance.*;
import com.vocasia.order.exception.CustomFeignException;
import com.vocasia.order.request.client.finance.NewInstructorBalanceHistoryRequest;
import com.vocasia.order.request.client.finance.NewInstructorIncomeRequest;
import com.vocasia.order.request.client.finance.NewPlatformBalanceHistoryRequest;
import com.vocasia.order.request.client.finance.NewPlatformIncomeRequest;
import com.vocasia.order.service.IFinanceService;
import com.vocasia.order.service.client.FinanceFeignClient;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class FinanceServiceImpl implements IFinanceService {

    private final Logger logger = LoggerFactory.getLogger(FinanceServiceImpl.class);

    private FinanceFeignClient financeFeignClient;

    @Override
    public InstructorIncomeDto saveInstructorIncome(NewInstructorIncomeRequest newInstructorIncomeRequest, String correlationId) {
        try {
            ResponseEntity<ResponseDto> financeFeignClientSaveInstructorIncomeResponseEntity = financeFeignClient.saveInstructorIncome(correlationId, newInstructorIncomeRequest);
            ResponseDto responseBody = financeFeignClientSaveInstructorIncomeResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();
            Map<String, Object> instructorIncome = data != null ? (Map<String, Object>) data.get("instructor_income") : null;

            logger.debug("FinanceServiceImpl.saveInstructorIncome() $instructorIncome:: {}", instructorIncome);

            InstructorIncomeDto instructorIncomeDto = new InstructorIncomeDto();

            if (instructorIncome != null) {
                instructorIncomeDto.setId(Long.valueOf(instructorIncome.get("id").toString()));
                instructorIncomeDto.setInstructorId(Long.valueOf(instructorIncome.get("instructor_id").toString()));
                instructorIncomeDto.setOrderId(Long.valueOf(instructorIncome.get("order_id").toString()));
                instructorIncomeDto.setCourseId(Long.valueOf(instructorIncome.get("course_id").toString()));
                instructorIncomeDto.setTotalUserPayment((Double) instructorIncome.get("total_user_payment"));
                instructorIncomeDto.setPlatformFeeInPercent(BigDecimal.valueOf((Integer) instructorIncome.get("platform_fee_in_percent")));
                instructorIncomeDto.setTotalPlatformFee((Double) instructorIncome.get("total_platform_fee"));
                instructorIncomeDto.setTotalInstructorIncome((Double) instructorIncome.get("total_instructor_income"));
                instructorIncomeDto.setRemarks((String) instructorIncome.get("remarks"));
                instructorIncomeDto.setCreatedAt(LocalDateTime.parse(instructorIncome.get("created_at").toString()));
                instructorIncomeDto.setUpdatedAt(LocalDateTime.parse(instructorIncome.get("updated_at").toString()));
            }

            return instructorIncomeDto;
        } catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }

    @Override
    public PlatformIncomeDto savePlatformIncome(NewPlatformIncomeRequest newPlatformIncomeRequest, String correlationId) {
        try {
            ResponseEntity<ResponseDto> financeFeignClientSaveInstructorIncomeResponseEntity = financeFeignClient.savePlatformIncome(correlationId, newPlatformIncomeRequest);
            ResponseDto responseBody = financeFeignClientSaveInstructorIncomeResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();
            Map<String, Object> platformIncome = data != null ? (Map<String, Object>) data.get("platform_income") : null;

            logger.debug("FinanceServiceImpl.savePlatformIncome() $platformIncome:: {}", platformIncome);

            PlatformIncomeDto platformIncomeDto = new PlatformIncomeDto();

            if (platformIncome != null) {
                platformIncomeDto.setId(Long.valueOf(platformIncome.get("id").toString()));
                platformIncomeDto.setInstructorId(Long.valueOf(platformIncome.get("instructor_id").toString()));
                platformIncomeDto.setOrderId(Long.valueOf(platformIncome.get("order_id").toString()));
                platformIncomeDto.setCourseId(Long.valueOf(platformIncome.get("course_id").toString()));
                platformIncomeDto.setTotalUserPayment((Double) platformIncome.get("total_user_payment"));
                platformIncomeDto.setPlatformFeeInPercent(BigDecimal.valueOf((Integer) platformIncome.get("platform_fee_in_percent")));
                platformIncomeDto.setTotalPlatformIncome((Double) platformIncome.get("total_platform_income"));
                platformIncomeDto.setRemarks((String) platformIncome.get("remarks"));
                platformIncomeDto.setCreatedAt(LocalDateTime.parse(platformIncome.get("created_at").toString()));
                platformIncomeDto.setUpdatedAt(LocalDateTime.parse(platformIncome.get("updated_at").toString()));
            }

            return platformIncomeDto;
        } catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }

    @Override
    public InstructorBalanceDto saveInstructorBalance(NewInstructorBalanceHistoryRequest newInstructorBalanceHistoryRequest, String correlationId) {
        try {
            ResponseEntity<ResponseDto> financeFeignClientSaveInstructorBalanceResponseEntity = financeFeignClient.saveInstructorBalance(correlationId, newInstructorBalanceHistoryRequest);
            ResponseDto responseBody = financeFeignClientSaveInstructorBalanceResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();
            Map<String, Object> instructorBalance = data != null ? (Map<String, Object>) data.get("instructor_balance") : null;

            logger.debug("FinanceServiceImpl.saveInstructorBalance() $instructorBalance:: {}", instructorBalance);

            InstructorBalanceDto instructorBalanceDto = new InstructorBalanceDto();

            if (instructorBalance != null) {
                instructorBalanceDto.setId(Long.valueOf(instructorBalance.get("id").toString()));
                instructorBalanceDto.setInstructorId(Long.valueOf(instructorBalance.get("instructor_id").toString()));
                instructorBalanceDto.setCurrentBalance((Double) instructorBalance.get("current_balance"));
                instructorBalanceDto.setTotalIncome((Double) instructorBalance.get("total_income"));
                instructorBalanceDto.setTotalPendingWithdrawal((Double) instructorBalance.get("total_pending_withdrawal"));
                instructorBalanceDto.setTotalWithdrawn((Double) instructorBalance.get("total_withdrawn"));
                instructorBalanceDto.setTotalPlatformFee((Double) instructorBalance.get("total_platform_fee"));
                instructorBalanceDto.setLastHistoryId(Long.valueOf(instructorBalance.get("last_history_id").toString()));
                instructorBalanceDto.setCreatedAt(LocalDateTime.parse(instructorBalance.get("created_at").toString()));
                instructorBalanceDto.setUpdatedAt(LocalDateTime.parse(instructorBalance.get("updated_at").toString()));
            }

            return instructorBalanceDto;
        } catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }

    @Override
    public PlatformBalanceDto savePlatformBalance(NewPlatformBalanceHistoryRequest newPlatformBalanceHistoryRequest, String correlationId) {
        try {
            ResponseEntity<ResponseDto> financeFeignClientSavePlatformBalanceResponseEntity = financeFeignClient.savePlatformBalance(correlationId, newPlatformBalanceHistoryRequest);
            ResponseDto responseBody = financeFeignClientSavePlatformBalanceResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();
            Map<String, Object> platformBalance = data != null ? (Map<String, Object>) data.get("platform_balance") : null;

            logger.debug("FinanceServiceImpl.savePlatformBalance() $platformBalance:: {}", platformBalance);

            PlatformBalanceDto platformBalanceDto = new PlatformBalanceDto();

            if (platformBalance != null) {
                platformBalanceDto.setId(Long.valueOf(platformBalance.get("id").toString()));
                platformBalanceDto.setCurrentBalance((Double) platformBalance.get("current_balance"));
                platformBalanceDto.setTotalIncome((Double) platformBalance.get("total_income"));
                platformBalanceDto.setTotalPendingWithdrawal((Double) platformBalance.get("total_pending_withdrawal"));
                platformBalanceDto.setTotalWithdrawn((Double) platformBalance.get("total_withdrawn"));
                platformBalanceDto.setLastHistoryId(Long.valueOf(platformBalance.get("last_history_id").toString()));
                platformBalanceDto.setCreatedAt(LocalDateTime.parse(platformBalance.get("created_at").toString()));
                platformBalanceDto.setUpdatedAt(LocalDateTime.parse(platformBalance.get("updated_at").toString()));
            }

            return platformBalanceDto;
        } catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }

    @Cacheable(value = "instructorIncomeDetail", key = "#orderId")
    @Override
    public List<InstructorIncomeDetailDto> findInstructorIncomesByOrderId(Long orderId, String correlationId) {
        try {
            ResponseEntity<ResponseDto> financeFeignClientResponseEntity = financeFeignClient.findInstructorIncomesByOrderId(correlationId, orderId);
            ResponseDto responseBody = financeFeignClientResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();
            List<Map<String, Object>> instructorIncomesData = data != null ? (List<Map<String, Object>>) data.get("instructor_incomes") : null;

            logger.debug("FinanceServiceImpl.findInstructorIncomesByOrderId() instructorIncomesData:: {}", instructorIncomesData);

            List<InstructorIncomeDetailDto> instructorIncomeDtos = new ArrayList<>();

            if (instructorIncomesData != null) {
                for (Map<String, Object> instructorIncomeData : instructorIncomesData) {
                    InstructorIncomeDetailDto instructorIncomeDetailDto = new InstructorIncomeDetailDto();
                    Map<String, Object> instructorIncome = (Map<String, Object>) instructorIncomeData.get("instructor_income");

                    InstructorIncomeDto instructorIncomeDto = new InstructorIncomeDto();
                    instructorIncomeDto.setId(Long.valueOf(instructorIncome.get("id").toString()));
                    instructorIncomeDto.setInstructorId(Long.valueOf(instructorIncome.get("instructor_id").toString()));
                    instructorIncomeDto.setOrderId(Long.valueOf(instructorIncome.get("order_id").toString()));
                    instructorIncomeDto.setCourseId(Long.valueOf(instructorIncome.get("course_id").toString()));
                    instructorIncomeDto.setTotalUserPayment((Double) instructorIncome.get("total_user_payment"));
                    instructorIncomeDto.setPlatformFeeInPercent(BigDecimal.valueOf((Integer) instructorIncome.get("platform_fee_in_percent")));
                    instructorIncomeDto.setTotalPlatformFee((Double) instructorIncome.get("total_platform_fee"));
                    instructorIncomeDto.setTotalInstructorIncome((Double) instructorIncome.get("total_instructor_income"));
                    instructorIncomeDto.setRemarks((String) instructorIncome.get("remarks"));
                    instructorIncomeDto.setCreatedAt(LocalDateTime.parse(instructorIncome.get("created_at").toString()));
                    instructorIncomeDto.setUpdatedAt(LocalDateTime.parse(instructorIncome.get("updated_at").toString()));

                    Map<String, Object> courseData = (Map<String, Object>) instructorIncomeData.get("course");

                    CourseDto courseDto = new CourseDto();
                    courseDto.setId(Long.valueOf(courseData.get("id").toString()));
                    courseDto.setInstructorId(Long.valueOf(courseData.get("instructor_id").toString()));
                    courseDto.setTitle(courseData.get("title").toString());
                    courseDto.setSlug(courseData.get("slug").toString());
                    courseDto.setLevel(courseData.get("level").toString());
                    courseDto.setLanguage(courseData.get("language").toString());
                    courseDto.setDescription(courseData.get("description").toString());
                    courseDto.setPrice(Double.valueOf(courseData.get("price").toString()));
                    courseDto.setDiscount(Double.valueOf(courseData.get("discount").toString()));
                    courseDto.setStatus(courseData.get("status").toString());
                    courseDto.setRating(Double.valueOf(courseData.get("rating").toString()));
                    courseDto.setTotalDuration(courseData.get("total_duration").toString());
                    courseDto.setShortDescription(courseData.get("short_description").toString());
                    courseDto.setFeaturedPicture(courseData.get("featured_picture").toString());
                    courseDto.setFeaturedPictureUrl(courseData.get("featured_picture_url").toString());
                    courseDto.setIsFree((Boolean) courseData.get("is_free"));
                    courseDto.setIsDiscount((Boolean) courseData.get("is_discount"));
                    courseDto.setChapterCount(Integer.valueOf(courseData.get("chapter_count").toString()));
                    courseDto.setLessonCount(Integer.valueOf(courseData.get("lesson_count").toString()));
                    courseDto.setRatingCount(Integer.valueOf(courseData.get("rating_count").toString()));
                    courseDto.setEnrollmentCount(Integer.valueOf(courseData.get("enrollment_count").toString()));
                    courseDto.setDeletedAt(courseData.get("deleted_at") != null ? LocalDateTime.parse(courseData.get("deleted_at").toString()) : null);
                    courseDto.setCreatedAt(LocalDateTime.parse(courseData.get("created_at").toString()));
                    courseDto.setUpdatedAt(LocalDateTime.parse(courseData.get("updated_at").toString()));

                    instructorIncomeDetailDto.setCourse(courseDto);
                    instructorIncomeDetailDto.setInstructorIncome(instructorIncomeDto);

                    instructorIncomeDtos.add(instructorIncomeDetailDto);
                }
            }

            return instructorIncomeDtos;
        } catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }

    @Cacheable(value = "platformIncomeDetail", key = "#orderId")
    @Override
    public List<PlatformIncomeDetailDto> findPlatformIncomesByOrderId(Long orderId, String correlationId) {
        try {
            ResponseEntity<ResponseDto> financeFeignClientResponseEntity = financeFeignClient.findPlatformIncomesByOrderId(correlationId, orderId);
            ResponseDto responseBody = financeFeignClientResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();
            List<Map<String, Object>> platformIncomesData = data != null ? (List<Map<String, Object>>) data.get("platform_incomes") : null;

            logger.debug("FinanceServiceImpl.findPlatformIncomesByOrderId() platformIncomesData:: {}", platformIncomesData);

            List<PlatformIncomeDetailDto> platformIncomeDtos = new ArrayList<>();

            if (platformIncomesData != null) {
                for (Map<String, Object> platformIncomeData : platformIncomesData) {
                    PlatformIncomeDetailDto platformIncomeDetailDto = new PlatformIncomeDetailDto();
                    Map<String, Object> platformIncome = (Map<String, Object>) platformIncomeData.get("platform_income");

                    PlatformIncomeDto platformIncomeDto = new PlatformIncomeDto();
                    platformIncomeDto.setId(Long.valueOf(platformIncome.get("id").toString()));
                    platformIncomeDto.setInstructorId(Long.valueOf(platformIncome.get("instructor_id").toString()));
                    platformIncomeDto.setOrderId(Long.valueOf(platformIncome.get("order_id").toString()));
                    platformIncomeDto.setCourseId(Long.valueOf(platformIncome.get("course_id").toString()));
                    platformIncomeDto.setTotalUserPayment((Double) platformIncome.get("total_user_payment"));
                    platformIncomeDto.setPlatformFeeInPercent(BigDecimal.valueOf((Integer) platformIncome.get("platform_fee_in_percent")));
                    platformIncomeDto.setTotalPlatformIncome((Double) platformIncome.get("total_platform_income"));
                    platformIncomeDto.setRemarks((String) platformIncome.get("remarks"));
                    platformIncomeDto.setCreatedAt(LocalDateTime.parse(platformIncome.get("created_at").toString()));
                    platformIncomeDto.setUpdatedAt(LocalDateTime.parse(platformIncome.get("updated_at").toString()));

                    Map<String, Object> courseData = (Map<String, Object>) platformIncomeData.get("course");

                    CourseDto courseDto = new CourseDto();
                    courseDto.setId(Long.valueOf(courseData.get("id").toString()));
                    courseDto.setInstructorId(Long.valueOf(courseData.get("instructor_id").toString()));
                    courseDto.setTitle(courseData.get("title").toString());
                    courseDto.setSlug(courseData.get("slug").toString());
                    courseDto.setLevel(courseData.get("level").toString());
                    courseDto.setLanguage(courseData.get("language").toString());
                    courseDto.setDescription(courseData.get("description").toString());
                    courseDto.setPrice(Double.valueOf(courseData.get("price").toString()));
                    courseDto.setDiscount(Double.valueOf(courseData.get("discount").toString()));
                    courseDto.setStatus(courseData.get("status").toString());
                    courseDto.setRating(Double.valueOf(courseData.get("rating").toString()));
                    courseDto.setTotalDuration(courseData.get("total_duration").toString());
                    courseDto.setShortDescription(courseData.get("short_description").toString());
                    courseDto.setFeaturedPicture(courseData.get("featured_picture").toString());
                    courseDto.setFeaturedPictureUrl(courseData.get("featured_picture_url").toString());
                    courseDto.setIsFree((Boolean) courseData.get("is_free"));
                    courseDto.setIsDiscount((Boolean) courseData.get("is_discount"));
                    courseDto.setChapterCount(Integer.valueOf(courseData.get("chapter_count").toString()));
                    courseDto.setLessonCount(Integer.valueOf(courseData.get("lesson_count").toString()));
                    courseDto.setRatingCount(Integer.valueOf(courseData.get("rating_count").toString()));
                    courseDto.setEnrollmentCount(Integer.valueOf(courseData.get("enrollment_count").toString()));
                    courseDto.setDeletedAt(courseData.get("deleted_at") != null ? LocalDateTime.parse(courseData.get("deleted_at").toString()) : null);
                    courseDto.setCreatedAt(LocalDateTime.parse(courseData.get("created_at").toString()));
                    courseDto.setUpdatedAt(LocalDateTime.parse(courseData.get("updated_at").toString()));

                    platformIncomeDetailDto.setPlatformIncome(platformIncomeDto);
                    platformIncomeDetailDto.setCourse(courseDto);

                    platformIncomeDtos.add(platformIncomeDetailDto);
                }
            }

            return platformIncomeDtos;
        } catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }

}
