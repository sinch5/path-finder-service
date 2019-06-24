package com.view.points.mostviews.services;

import com.netflix.discovery.EurekaClient;
import com.view.points.mostviews.googlemapsentities.DirectMatrixResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.text.MessageFormat;
import java.util.List;
import java.util.function.BinaryOperator;

public class DistanceMatrixServiceImpl implements DistanceMatrixService {

    private final WebClient webClient;

    private static final String DISTANCE_MATRIX_API="google-distance-matrix-api-adapter";

    private LoadBalancerClient loadBalancer;

    public DistanceMatrixServiceImpl(WebClient webClient, LoadBalancerClient loadBalancer) {
        this.loadBalancer = loadBalancer;
        this.webClient = webClient;
    }

    public Mono<DirectMatrixResponse> buildMatrix(String locations) {

        ServiceInstance instance = loadBalancer.choose(DISTANCE_MATRIX_API);
        URI storesUri = URI.create(String.format("http://%s:%s", instance.getHost(), instance.getPort()));

        return webClient.get()
                .uri(MessageFormat.format("{0}/routes/{1}",storesUri,locations))
                .exchange().
                        flatMap(clientResponse -> clientResponse.bodyToMono(DirectMatrixResponse.class));
    }
}
