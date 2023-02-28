<<<<<<< HEAD
package balu.sbms.springcloudapigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class CustomFilter implements GlobalFilter {
    Logger logger = LoggerFactory.getLogger(CustomFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if(request.getURI().toString().contains("/student/")){
            logger.info("Working on Student service  + URL: "+ request.getURI().toString());
        } else if(request.getURI().toString().contains("/address/")){
            logger.info("Working on Address service  + URL: "+ request.getURI().toString());
        }
        logger.info("Authorization: "+ request.getHeaders().getFirst("Authorization"));
        return chain.filter(exchange).then(Mono.fromRunnable(()-> {
            ServerHttpResponse response = exchange.getResponse();
            logger.info("Post Filter: " + response.getStatusCode());
        }));
    }
}
=======
package balu.sbms.springcloudapigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class CustomFilter implements GlobalFilter {
    Logger logger = LoggerFactory.getLogger(CustomFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if(request.getURI().toString().contains("/student/")){
            logger.info("Working on Student service  + URL: "+ request.getURI().toString());
        } else if(request.getURI().toString().contains("/address/")){
            logger.info("Working on Address service  + URL: "+ request.getURI().toString());
        }
        logger.info("Authorization: "+ request.getHeaders().getFirst("Authorization"));
        return chain.filter(exchange).then(Mono.fromRunnable(()-> {
            ServerHttpResponse response = exchange.getResponse();
            logger.info("Post Filter: " + response.getStatusCode());
        }));
    }
}
>>>>>>> ea54f86209dd783f241e624b01f5009754e50877
