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

                        .pathMatchers(HttpMethod.GET, "/playground/config").permitAll()

                        // authentication service
                        .pathMatchers("/auth/actuator/**").permitAll()

                        .pathMatchers(HttpMethod.GET, "/auth/build-info").permitAll()
                        .pathMatchers(HttpMethod.GET,"/auth/welcome").permitAll()
                        .pathMatchers(HttpMethod.GET,"/auth/user/{id}").permitAll()
                        .pathMatchers(HttpMethod.POST,"/auth/register").permitAll()
                        .pathMatchers(HttpMethod.POST,"/auth/login").permitAll()
                        .pathMatchers(HttpMethod.POST,"/auth/refresh-token").hasAnyRole("ADMIN", "LECTURER", "STUDENT")
                        .pathMatchers(HttpMethod.PUT,"/auth/user/{userId}/update-user").permitAll()

                        // instructors service
                        .pathMatchers("/instructors/actuator/**").permitAll()

                        .pathMatchers(HttpMethod.GET,"/instructors/build-info").permitAll()
                        .pathMatchers(HttpMethod.GET,"/instructors/welcome").permitAll()
                        .pathMatchers(HttpMethod.POST,"/instructors/register").permitAll()
                        .pathMatchers(HttpMethod.GET,"/instructors/profile/{instructorId}").permitAll()
                        .pathMatchers(HttpMethod.GET,"/instructors/profile-by-user-id/{userId}").permitAll()
                        .pathMatchers(HttpMethod.PUT, "/instructors/profile").hasRole("INSTRUCTOR")

                        // course service
                        .pathMatchers("/course/actuator/**").permitAll()

                        .pathMatchers(HttpMethod.GET,"/course/build-info").permitAll()
                        .pathMatchers(HttpMethod.GET,"/course/welcome").permitAll()

                        .pathMatchers(HttpMethod.GET,"/course/categories").permitAll()

                        .pathMatchers(HttpMethod.GET,"/course/admin/categories").permitAll()
                        .pathMatchers(HttpMethod.POST,"/course/admin/categories").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.GET,"/course/admin/categories/{categoryId}").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.PUT,"/course/admin/categories/{categoryId}").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE,"/course/admin/categories/{categoryId}").hasRole("ADMIN")

                        .pathMatchers(HttpMethod.GET,"/course/courses/all").hasAnyRole("INSTRUCTOR", "ADMIN")
                        .pathMatchers(HttpMethod.GET,"/course/courses/draft").hasAnyRole("INSTRUCTOR", "ADMIN")
                        .pathMatchers(HttpMethod.GET,"/course/courses/published").hasAnyRole("INSTRUCTOR", "ADMIN")

                        .pathMatchers(HttpMethod.POST,"/course/courses").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.GET,"/course/courses/{courseId}").permitAll()
                        .pathMatchers(HttpMethod.PUT, "/course/courses/{courseId}/thumbnail").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.PUT, "/course/courses/{courseId}").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.POST, "/course/courses/{courseId}/publish").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.GET, "/course/courses/{courseId}/students").hasAnyRole("INSTRUCTOR", "ADMIN")

                        .pathMatchers(HttpMethod.GET, "/course/{courseId}/chapters").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.POST, "/course/{courseId}/chapters").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.GET, "/course/{courseId}/chapters/{chapterId}").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.PUT, "/course/{courseId}/chapters/{chapterId}").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.DELETE, "/course/{courseId}/chapters/{chapterId}").hasRole("INSTRUCTOR")

                        .pathMatchers(HttpMethod.GET, "/course/{courseId}/chapters/{chapterId}/lessons").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.POST, "/course/{courseId}/chapters/{chapterId}/lessons").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.GET, "/course/{courseId}/chapters/{chapterId}/lessons/{lessonId}").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.PUT, "/course/{courseId}/chapters/{chapterId}/lessons/{lessonId}").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.DELETE, "/course/{courseId}/chapters/{chapterId}/lessons/{lessonId}").hasRole("INSTRUCTOR")

                        .pathMatchers(HttpMethod.GET, "/course/public/categories").permitAll()
                        .pathMatchers(HttpMethod.GET, "/course/public/editor-choices").permitAll()
                        .pathMatchers(HttpMethod.GET, "/course/public/{slug}/{courseId}/overview").permitAll()
                        .pathMatchers(HttpMethod.GET, "/course/public/{slug}/{courseId}/chapters").permitAll()

                        // order service
                        .pathMatchers("/order/actuator/**").permitAll()

                        .pathMatchers(HttpMethod.GET,"/order/build-info").permitAll()
                        .pathMatchers(HttpMethod.GET,"/order/welcome").permitAll()

                        .pathMatchers(HttpMethod.POST, "/order/place-new-order").hasRole("STUDENT")
                        .pathMatchers(HttpMethod.GET, "/order/get-data/{orderId}").hasRole("STUDENT")
                        .pathMatchers(HttpMethod.PUT, "/order/update-payment-status/{orderId}").permitAll()

                        // payment service
                        .pathMatchers("/payment/actuator/**").permitAll()

                        .pathMatchers(HttpMethod.GET,"/payment/build-info").permitAll()
                        .pathMatchers(HttpMethod.GET,"/payment/welcome").permitAll()

                        .pathMatchers(HttpMethod.POST, "/payment/create-order-payment").hasRole("STUDENT")
                        .pathMatchers(HttpMethod.GET, "/payment/payment-data-by-order-id/{orderId}").hasRole("STUDENT")

                        .pathMatchers(HttpMethod.POST, "/payment/midtrans-callback").permitAll()

                        // enrollment service
                        .pathMatchers("/enrollment/actuator/**").permitAll()

                        .pathMatchers(HttpMethod.GET,"/enrollment/build-info").permitAll()
                        .pathMatchers(HttpMethod.GET,"/enrollment/welcome").permitAll()

                        .pathMatchers(HttpMethod.POST,"/enrollment/enroll").hasRole("STUDENT")
                        .pathMatchers(HttpMethod.GET,"/enrollment/courses").hasRole("STUDENT")
                        .pathMatchers(HttpMethod.GET,"/enrollment/courses/{enrollmentId}").hasRole("STUDENT")

                        .pathMatchers(HttpMethod.GET,"/enrollment/course-data/{courseId}/students").hasAnyRole("INSTRUCTOR", "ADMIN")

                        // finance service
                        .pathMatchers("/finance/actuator/**").permitAll()

                        .pathMatchers(HttpMethod.GET,"/finance/build-info").permitAll()
                        .pathMatchers(HttpMethod.GET,"/finance/welcome").permitAll()

                        .pathMatchers(HttpMethod.POST, "/finance/instructor-income/store").permitAll()
                        .pathMatchers(HttpMethod.POST, "/finance/platform-income/store").permitAll()
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
