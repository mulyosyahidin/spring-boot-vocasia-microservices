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


                        // course service
                        .pathMatchers("/course/actuator/**").permitAll()

                        .pathMatchers(HttpMethod.GET, "/course/build-info").permitAll()
                        .pathMatchers(HttpMethod.GET, "/course/welcome").permitAll()

                        .pathMatchers(HttpMethod.GET, "/course/categories").permitAll()

                        .pathMatchers(HttpMethod.GET, "/course/admin/categories").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.GET, "/course/admin/categories/only-parents").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.POST, "/course/admin/categories").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.GET, "/course/admin/categories/{categoryId}").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.PUT, "/course/admin/categories/{categoryId}").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "/course/admin/categories/{categoryId}").hasRole("ADMIN")

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

                        .pathMatchers(HttpMethod.GET, "/course/public/categories").permitAll()

                        .pathMatchers(HttpMethod.GET, "/course/public/courses/editor-choices").permitAll()
                        .pathMatchers(HttpMethod.GET, "/course/public/courses/{slug}/{courseId}").permitAll()
                        .pathMatchers(HttpMethod.GET, "/course/public/courses/{slug}/{courseId}/contents").permitAll()

                        .pathMatchers(HttpMethod.GET, "/course/student/courses/{courseId}").permitAll()
                        .pathMatchers(HttpMethod.GET, "/course/student/courses/{courseId}/contents").permitAll()

                        // order service
                        .pathMatchers("/order/actuator/**").permitAll()

                        .pathMatchers(HttpMethod.GET, "/order/build-info").permitAll()
                        .pathMatchers(HttpMethod.GET, "/order/welcome").permitAll()

                        .pathMatchers(HttpMethod.GET, "/order/instructor/orders/{orderId}").hasRole("INSTRUCTOR")

                        .pathMatchers(HttpMethod.POST, "/order/student/place-new-order").hasRole("STUDENT")
                        .pathMatchers(HttpMethod.GET, "/order/student/orders").hasRole("STUDENT")
                        .pathMatchers(HttpMethod.GET, "/order/student/orders/{orderId}").hasRole("STUDENT")

                        .pathMatchers(HttpMethod.PUT, "/order/update-payment-status/{orderId}").permitAll()


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


                        // finance service
                        .pathMatchers("/finance/actuator/**").permitAll()

                        .pathMatchers(HttpMethod.GET, "/finance/build-info").permitAll()
                        .pathMatchers(HttpMethod.GET, "/finance/welcome").permitAll()

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
