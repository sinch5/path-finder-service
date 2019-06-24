package com.view.points.mostviews.services;

import com.view.points.mostviews.entities.PlaceResponse;
import com.view.points.mostviews.googlemapsentities.PlaceDetails;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.text.MessageFormat;

public class PlaceDetailsServiceImpl implements PlaceDetailsService {

    private final WebClient webClient;

    private static final String PLACE_DETAILS_API="google-place-details-service";

    private LoadBalancerClient loadBalancer;

    public PlaceDetailsServiceImpl(WebClient webClient, LoadBalancerClient loadBalancer) {
        this.loadBalancer = loadBalancer;
        this.webClient = webClient;
    }

    public Mono<PlaceDetails> findById(String name) {

        ServiceInstance instance = loadBalancer.choose(PLACE_DETAILS_API);
        URI storesUri = URI.create(String.format("http://%s:%s", instance.getHost(), instance.getPort()));

        return webClient.get()
                .uri(MessageFormat.format("{0}/place/{1}",storesUri,name))
                .exchange().
                        flatMap(clientResponse -> clientResponse.bodyToMono(PlaceDetails.class));
    }
}
