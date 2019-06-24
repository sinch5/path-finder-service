package com.view.points.mostviews;

import com.view.points.mostviews.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@TestConfiguration()
public class TestConfig {

    @MockBean
    private LoadBalancerClient loadBalancerClient;

    @Bean
    public GraphBuilderService getGraphBuilderService(@Autowired DistanceMatrixService distanceMatrixService, PlaceDetailsService placeDetailsService,  PlacesService placesService) {
        return new GraphBuilderServiceImpl(distanceMatrixService, placeDetailsService, placesService);
    }

    @Bean
    public PathFinderService getPathFinderService() {
        return new PathFinderServiceImpl();
    }

    @Bean
    public PlacesService  getPlacesService(@Autowired WebClient webClient) {
        return new PlacesServiceImpl(webClient, loadBalancerClient);
    }


    @Bean
    public PlaceDetailsService  getPlaceDetailsService(@Autowired WebClient webClient) {
        return new PlaceDetailsServiceImpl(webClient, loadBalancerClient);
    }
  /*  @Bean
    public TripAdvisorService getTripAdvisorService( PathFinderService pathFinderService, GraphBuilderService graphBuilderService) {
        return new TripAdvisorServiceImpl(pathFinderService, graphBuilderService);
    }*/


}
