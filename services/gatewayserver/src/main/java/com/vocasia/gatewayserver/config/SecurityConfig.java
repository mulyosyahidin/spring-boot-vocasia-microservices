package com.vocasia.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity.authorizeExchange(exchanges -> exchanges
                        .pathMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                        .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // authentication service
                        .pathMatchers("/authentication/actuator/**").permitAll()

                        .pathMatchers(HttpMethod.GET, "/authentication/build-info").permitAll()
                        .pathMatchers(HttpMethod.GET, "/authentication/welcome").permitAll()

                        .pathMatchers(HttpMethod.GET, "/authentication/user/{id}").permitAll()
                        .pathMatchers(HttpMethod.POST, "/authentication/register").permitAll()
                        .pathMatchers(HttpMethod.POST, "/authentication/login").permitAll()
                        .pathMatchers(HttpMethod.PUT, "/authentication/user/{userId}/update-user").permitAll()
                        .pathMatchers(HttpMethod.POST, "/authentication/refresh-token").hasAnyRole("ADMIN", "LECTURER", "STUDENT")

                        .pathMatchers(HttpMethod.GET, "/authentication/admin/students").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.GET, "/authentication/admin/students/{userId}").hasRole("ADMIN")

                        // instructors service
                        .pathMatchers("/instructor/actuator/**").permitAll()

                        .pathMatchers(HttpMethod.GET, "/instructor/build-info").permitAll()
                        .pathMatchers(HttpMethod.GET, "/instructor/welcome").permitAll()

                        .pathMatchers(HttpMethod.POST, "/instructor/register").permitAll()

                        .pathMatchers(HttpMethod.GET, "/instructor/instructor/students").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.GET, "/instructor/instructor/students/{id}").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.GET, "/instructor/instructor/profile").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.PUT, "/instructor/instructor/profile").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.GET, "/instructor/profile/{id}").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.GET, "/instructor/profile-by-user-id/{userId}").permitAll()

                        .pathMatchers(HttpMethod.POST, "/instructor/student/assign-courses").permitAll()

                        .pathMatchers(HttpMethod.GET, "/instructor/admin/instructors").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.GET, "/instructor/admin/instructors/{instructorId}").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.GET, "/instructor/admin/students").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.GET, "/instructor/admin/students/{id}").hasRole("ADMIN")

                        .pathMatchers(HttpMethod.GET, "/instructor/overview").hasRole("INSTRUCTOR")

                        // course service
                        .pathMatchers("/course/actuator/**").permitAll()

                        .pathMatchers(HttpMethod.GET, "/course/build-info").permitAll()
                        .pathMatchers(HttpMethod.GET, "/course/welcome").permitAll()

                        .pathMatchers(HttpMethod.GET, "/course/categories").permitAll()

                        .pathMatchers(HttpMethod.GET, "/course/admin/categories").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.GET, "/course/admin/categories/only-parents").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.POST, "/course/admin/categories/sync").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.POST, "/course/admin/categories").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.GET, "/course/admin/categories/{categoryId}").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.PUT, "/course/admin/categories/{categoryId}").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "/course/admin/categories/{categoryId}").hasRole("ADMIN")

                        .pathMatchers(HttpMethod.GET, "/course/admin/courses").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.GET, "/course/admin/courses/{courseId}").hasRole("ADMIN")

                        .pathMatchers(HttpMethod.GET, "/course/instructor/categories").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.POST, "/course/instructor/courses").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.PUT, "/course/instructor/courses/{courseId}/thumbnail").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.GET, "/course/instructor/courses/{courseId}").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.PUT, "/course/instructor/courses/{courseId}").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.POST, "/course/instructor/courses/{courseId}/publish").hasRole("INSTRUCTOR")

                        .pathMatchers(HttpMethod.GET, "/course/instructor/courses/all").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.GET, "/course/instructor/courses/draft").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.GET, "/course/instructor/courses/published").hasRole("INSTRUCTOR")

                        .pathMatchers(HttpMethod.GET, "/course/instructor/courses/{courseId}/chapters").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.POST, "/course/instructor/courses/{courseId}/chapters").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.GET, "/course/instructor/courses/{courseId}/chapters/{chapterId}").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.PUT, "/course/instructor/courses/{courseId}/chapters/{chapterId}").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.DELETE, "/course/instructor/courses/{courseId}/chapters/{chapterId}").hasRole("INSTRUCTOR")

                        .pathMatchers(HttpMethod.GET, "/course/instructor/courses/{courseId}/chapters/{chapterId}/lessons").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.POST, "/course/instructor/courses/{courseId}/chapters/{chapterId}/lessons").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.GET, "/course/instructor/courses/{courseId}/chapters/{chapterId}/lessons/{lessonId}").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.PUT, "/course/instructor/courses/{courseId}/chapters/{chapterId}/lessons/{lessonId}").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.DELETE, "/course/instructor/courses/{courseId}/chapters/{chapterId}/lessons/{lessonId}").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.GET, "/course/instructor/courses/lessons/{lessonId}").hasRole("INSTRUCTOR")

                        .pathMatchers(HttpMethod.GET, "/course/instructor/overview").hasRole("INSTRUCTOR")

                        .pathMatchers(HttpMethod.GET, "/course/public/categories").permitAll()

                        .pathMatchers(HttpMethod.GET, "/course/public/courses/editor-choices").permitAll()
                        .pathMatchers(HttpMethod.GET, "/course/public/courses/{slug}/{courseId}").permitAll()
                        .pathMatchers(HttpMethod.GET, "/course/public/courses/{slug}/{courseId}/contents").permitAll()

                        .pathMatchers(HttpMethod.GET, "/course/student/courses/{courseId}").permitAll()
                        .pathMatchers(HttpMethod.GET, "/course/student/courses/{courseId}/contents").permitAll()

                        .pathMatchers(HttpMethod.GET, "/course/admin/instructor-courses").hasRole("ADMIN")


                        // order service
                        .pathMatchers("/order/actuator/**").permitAll()

                        .pathMatchers(HttpMethod.GET, "/order/build-info").permitAll()
                        .pathMatchers(HttpMethod.GET, "/order/welcome").permitAll()

                        .pathMatchers(HttpMethod.GET, "/order/instructor/orders/{orderId}").hasRole("INSTRUCTOR")

                        .pathMatchers(HttpMethod.POST, "/order/student/place-new-order").hasRole("STUDENT")
                        .pathMatchers(HttpMethod.GET, "/order/student/orders").hasRole("STUDENT")
                        .pathMatchers(HttpMethod.GET, "/order/student/orders/{orderId}").hasRole("STUDENT")

                        .pathMatchers(HttpMethod.PUT, "/order/update-payment-status/{orderId}").permitAll()

                        .pathMatchers(HttpMethod.GET, "/order/admin/transactions").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.GET, "/order/admin/transactions/{orderId}").hasRole("ADMIN")

                        .pathMatchers(HttpMethod.GET, "/order/admin/student/transactions").hasRole("ADMIN")

                        // payment service
                        .pathMatchers(HttpMethod.GET, "/payment/actuator/**").permitAll()

                        .pathMatchers(HttpMethod.GET, "/payment/build-info").permitAll()
                        .pathMatchers(HttpMethod.GET, "/payment/welcome").permitAll()

                        .pathMatchers(HttpMethod.POST, "/payment/student/create-order-payment").hasRole("STUDENT")
                        .pathMatchers(HttpMethod.GET, "/payment/student/orders/{orderId}").hasRole("STUDENT")
                        .pathMatchers(HttpMethod.GET, "/payment/instructor/orders/{orderId}").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.POST, "/payment/midtrans-callback").permitAll()

                        // enrollment service
                        .pathMatchers("/enrollment/actuator/**").permitAll()

                        .pathMatchers(HttpMethod.GET, "/enrollment/build-info").permitAll()
                        .pathMatchers(HttpMethod.GET, "/enrollment/welcome").permitAll()

                        .pathMatchers(HttpMethod.GET, "/enrollment/instructor/courses/{courseId}/students").hasAnyRole("INSTRUCTOR", "ADMIN")

                        .pathMatchers(HttpMethod.GET, "/enrollment/student/courses").hasRole("STUDENT")
                        .pathMatchers(HttpMethod.POST, "/enrollment/student/enroll-courses").hasRole("STUDENT")
                        .pathMatchers(HttpMethod.GET, "/enrollment/student/courses/{courseId}/is-user-enrolled").permitAll()

                        .pathMatchers(HttpMethod.GET, "/enrollment/student/courses/{enrollmentId}").hasRole("STUDENT")

                        .pathMatchers(HttpMethod.POST, "/enrollment/student/progress/{enrollmentId}/set-last-access-lesson").hasRole("STUDENT")
                        .pathMatchers(HttpMethod.POST, "/enrollment/student/progress/{enrollmentId}/start-lesson/{lessonId}").hasRole("STUDENT")
                        .pathMatchers(HttpMethod.POST, "/enrollment/student/progress/{enrollmentId}/complete-lesson/{lessonId}").hasRole("STUDENT")
                        .pathMatchers(HttpMethod.GET, "/enrollment/student/progress/{enrollmentId}/is-lesson-complete/{lessonId}").hasRole("STUDENT")

                        .pathMatchers(HttpMethod.GET, "/enrollment/admin/student/courses").hasRole("ADMIN")

                        // finance service
                        .pathMatchers("/finance/actuator/**").permitAll()

                        .pathMatchers(HttpMethod.GET, "/finance/build-info").permitAll()
                        .pathMatchers(HttpMethod.GET, "/finance/welcome").permitAll()

                        .pathMatchers(HttpMethod.GET, "/finance/instructor/overview").hasRole("INSTRUCTOR")

                        .pathMatchers(HttpMethod.GET, "/finance/instructor/transactions").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.GET, "/finance/instructor/transactions/{id}").hasRole("INSTRUCTOR")

                        .pathMatchers(HttpMethod.GET, "/finance/instructor/withdrawal").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.POST, "/finance/instructor/withdrawal").hasRole("INSTRUCTOR")

                        .pathMatchers(HttpMethod.GET, "/finance/instructor/balance/data").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.GET, "/finance/instructor/courses/{courseId}/income").hasRole("INSTRUCTOR")

                        .pathMatchers(HttpMethod.POST, "/finance/instructor-balance/store").permitAll()
                        .pathMatchers(HttpMethod.POST, "/finance/platform-balance/store").permitAll()

                        .pathMatchers(HttpMethod.POST, "/finance/instructor-income/store").permitAll()
                        .pathMatchers(HttpMethod.POST, "/finance/platform-income/store").permitAll()

                        .pathMatchers(HttpMethod.GET, "/finance/admin/withdrawal/request").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.GET, "/finance/admin/withdrawal/request/{id}").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.POST, "/finance/admin/withdrawal/request/{id}/process").hasRole("ADMIN")

                        .pathMatchers(HttpMethod.GET, "/finance/admin/instructor-income/{orderId}").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.GET, "/finance/admin/platform-income/{orderId}").hasRole("ADMIN")

                        .pathMatchers(HttpMethod.GET, "/finance/admin/balance/data").hasRole("ADMIN")

                        .pathMatchers(HttpMethod.GET, "/finance/admin/transactions").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.GET, "/finance/admin/transactions/{id}").hasRole("ADMIN")

                        .pathMatchers(HttpMethod.GET, "/finance/admin/instructor/balance/data").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.GET, "/finance/admin/instructor/withdrawal/request").hasRole("ADMIN")


                        // QNA SERVICE
                        .pathMatchers("/qna/actuator/**").permitAll()

                        .pathMatchers(HttpMethod.GET, "/qna/build-info").permitAll()
                        .pathMatchers(HttpMethod.GET, "/qna/welcome").permitAll()

                        .pathMatchers(HttpMethod.GET, "/qna/student/is-i-ask-this-lesson/{lessonId}").hasRole("STUDENT")
                        .pathMatchers(HttpMethod.GET, "/qna/student/questions/{courseId}/{lessonId}").hasRole("STUDENT")
                        .pathMatchers(HttpMethod.POST, "/qna/student/questions/{courseId}/{lessonId}").hasRole("STUDENT")
                        .pathMatchers(HttpMethod.GET, "/qna/student/questions/{lessonId}/qna/{qnaId}").hasRole("STUDENT")
                        .pathMatchers(HttpMethod.POST, "/qna/student/questions/{lessonId}/qna/{qnaId}").hasRole("STUDENT")

                        .pathMatchers(HttpMethod.GET, "/qna/instructor/courses/{courseId}/questions").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.GET, "/qna/instructor/courses/{courseId}/questions/{qnaId}").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.POST, "/qna/instructor/courses/{courseId}/questions/{qnaId}").hasRole("INSTRUCTOR")


                        // QNA SERVICE
                        .pathMatchers("/catalog/actuator/**").permitAll()

                        .pathMatchers(HttpMethod.GET, "/catalog/build-info").permitAll()
                        .pathMatchers(HttpMethod.GET, "/catalog/welcome").permitAll()

                        .pathMatchers(HttpMethod.GET, "/catalog/admin/categories").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.POST, "/catalog/admin/categories").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.POST, "/catalog/admin/categories/sync").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.PUT, "/catalog/admin/categories/{categoryId}").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "/catalog/admin/categories/{categoryId}").hasRole("ADMIN")

                        .pathMatchers(HttpMethod.GET, "/catalog/instructor/courses").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.POST, "/catalog/instructor/courses").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.PUT, "/catalog/instructor/courses/{courseId}").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.DELETE, "/catalog/instructor/courses/{courseId}").hasRole("INSTRUCTOR")
                )
                .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec
                        .jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(grantedAuthoritiesExtractor())));

        serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable);

        return serverHttpSecurity.build();
    }

    private Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

}
