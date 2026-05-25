package com.formationkilo.gateway_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.List;

@SpringBootApplication
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	@Bean
	public DiscoveryClientRouteDefinitionLocator dynamicRoutes(
			ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp){
		return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);

	}
}

/* @SpringBootApplication

public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	@Bean
	public DiscoveryClientRouteDefinitionLocator dynamicRoutes(
			ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp) {

		PredicateDefinition predicate = new PredicateDefinition();
		predicate.setName("Path");
		predicate.addArg("pattern", "'/api/' + serviceId + '/**'");

		FilterDefinition filter = new FilterDefinition();
		filter.setName("RewritePath");
		filter.addArg("regexp", "'/api/' + serviceId + '/(?<remaining>.*)'");
		filter.addArg("replacement", "'/${remaining}'");

		dlp.setPredicates(List.of(predicate));
		dlp.setFilters(List.of(filter));

		return new DiscoveryClientRouteDefinitionLocator(rdc, dlp);
	}

	@Bean
	public GlobalFilter forwardedHeadersFilter() {
		return new GlobalFilter() {
			@Override
			public reactor.core.publisher.Mono<Void> filter(
					org.springframework.web.server.ServerWebExchange exchange,
					org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {

				String path = exchange.getRequest().getPath().toString();
				String prefix = "";
				if (path.startsWith("/api/")) {
					String[] parts = path.split("/");
					if (parts.length >= 3) {
						prefix = "/api/" + parts[2];
					}
				}

				ServerHttpRequest request = exchange.getRequest().mutate()
						.header("X-Forwarded-Host", "localhost:8888")
						.header("X-Forwarded-Proto", "http")
						.header("X-Forwarded-Prefix", prefix)
						.build();

				return chain.filter(exchange.mutate().request(request).build());
			}
		};
	}
}*/