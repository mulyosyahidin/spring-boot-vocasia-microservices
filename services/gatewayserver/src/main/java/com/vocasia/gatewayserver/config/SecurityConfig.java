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

                        // instructors service
                        .pathMatchers("/instructors/actuator/**").permitAll()

                        .pathMatchers(HttpMethod.GET,"/instructors/build-info").permitAll()
                        .pathMatchers(HttpMethod.GET,"/instructors/welcome").permitAll()
                        .pathMatchers(HttpMethod.POST,"/instructors/register").permitAll()
                        .pathMatchers(HttpMethod.GET,"/instructors/profile/**").permitAll()
                        .pathMatchers(HttpMethod.GET,"/instructors/profile-by-user-id/{userId}").permitAll()

                        // course service
                        .pathMatchers("/course/actuator/**").permitAll()

                        .pathMatchers(HttpMethod.GET,"/course/build-info").permitAll()
                        .pathMatchers(HttpMethod.GET,"/course/welcome").permitAll()

                        .pathMatchers(HttpMethod.GET,"/course/categories").permitAll()
                        .pathMatchers(HttpMethod.GET,"/course/categories/{categoryId}").permitAll()

                        .pathMatchers(HttpMethod.GET,"/course/courses/draft").hasAnyRole("INSTRUCTOR", "ADMIN")
                        .pathMatchers(HttpMethod.POST,"/course/courses").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.GET,"/course/courses/{courseId}").permitAll()
                        .pathMatchers(HttpMethod.PUT, "/course/courses/{courseId}/thumbnail").hasRole("INSTRUCTOR")
                        .pathMatchers(HttpMethod.PUT, "/course/courses/{courseId}").hasRole("INSTRUCTOR")

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
