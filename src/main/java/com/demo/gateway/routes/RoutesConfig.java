package com.demo.gateway.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Configuration
public class RoutesConfig {
    @Value("${uri.api.clientes}")
    private String clientesUri;

    @Value("${uri.api.cuentas}")
    private String cuentasUri;
/*
    @Bean
    public RouteLocator rutasDeClientes(RouteLocatorBuilder builder) {

        return builder.routes()
                .route("all_clientes", r -> r.path("/promerica/cliente/all")
                        .filters(f -> f.rewritePath("/promerica/","/v1/api/")
                                .addRequestHeader("X-GATEWAY-REQUEST-HEADER", "GATEWAY-SOLICITUD")
                                .addResponseHeader("X-GATEWAY-RESPONSE-HEADER", "GATEWAY-RESPONSE"))
                                .uri(clientesUri))
                .route("cliente_id", r -> r.path("/v1/api/cliente/{id}")
                        .and().method(HttpMethod.POST)
                        .filters(f -> f.addRequestHeader("X-GATEWAY-REQUEST-HEADER", "GATEWAY-SOLICITUD")
                                .addResponseHeader("X-GATEWAY-RESPONSE-HEADER", "GATEWAY-RESPONSE"))
                        .uri(clientesUri))
                .build();

    }

    @Bean
    public RouteLocator rutasDeCuentas(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("all_cuentas", r -> r.path("/api/v2/pokemon/ditto")
                        .filters(f -> f.addRequestHeader("X-GATEWAY-REQUEST-HEADER", "GATEWAY-SOLICITUD")
                                .addResponseHeader("X-GATEWAY-RESPONSE-HEADER", "GATEWAY-RESPONSE"))
                        .uri(cuentasUri))
                .build();

    }

 */
    @Bean
    public KeyResolver userKeyResolver(){
        return exchange -> Mono.just(Objects.requireNonNull(
                exchange
                        .getRequest()
                        .getRemoteAddress()
                )
                .getAddress()
                .getHostAddress());
    }
}
