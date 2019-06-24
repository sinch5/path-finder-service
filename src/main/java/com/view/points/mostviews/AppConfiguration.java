package com.view.points.mostviews;

import com.view.points.mostviews.services.DistanceMatrixServiceImpl;
import com.view.points.mostviews.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfiguration {

    @Value("${base.googlemaps.url}")
    private String routes;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Bean
    public WebClient getWebClient() {
        return  WebClient.create(routes);
    }

    @Bean
    public GraphBuilderService getGraphBuilderService(@Autowired DistanceMatrixService distanceMatrixService, PlaceDetailsService placeDetailsService,  PlacesService placesService) {
        return new GraphBuilderServiceImpl(distanceMatrixService, placeDetailsService, placesService);
    }

    @Bean
    public PathFinderService getPathFinderService() {
        return new PathFinderServiceImpl();
    }

    @Bean
    public TripAdvisorService getTripAdvisorService(@Autowired PathFinderService pathFinderService, GraphBuilderService graphBuilderService) {
        return new TripAdvisorServiceImpl(pathFinderService, graphBuilderService);
    }

    @Bean
    public DistanceMatrixService getDirectMatrixService(@Autowired WebClient webClient) {
        return new DistanceMatrixServiceImpl(webClient,  loadBalancerClient);
    }


    @Bean
    public PlacesService  getPlacesService(@Autowired WebClient webClient) {
        return new PlacesServiceImpl(webClient, loadBalancerClient);
    }


    @Bean
    public PlaceDetailsService  getPlaceDetailsService(@Autowired WebClient webClient) {
        return new PlaceDetailsServiceImpl(webClient, loadBalancerClient);
    }
}
