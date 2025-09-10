package com.kuklin.apigateway.configurations;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfiguration {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r
                        .path("/api/v1/users/**")
                        .uri("lb://user-service"))
                .route("ai-conversation-service", r -> r
                        .path("/api/v1/messages**")
                        .and()
                        .path("/api/v1/conversations")
                        .uri("lb://ai-conversation-service"))
                .route("interview-service", r -> r
                        .path("/api/v1/interview**")
                        .and()
                        .path("/api/v1/skill")
                        .and()
                        .path("/api/v1/topic")
                        .and()
                        .path("/api/v1/progress")
                        .and()
                        .path("/api/v1/vacancy")
                        .uri("lb://interview-service"))
                .build();
    }

}
