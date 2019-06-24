package com.view.points.mostviews.controller;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.view.points.mostviews.model.Way;
import com.view.points.mostviews.services.TripAdvisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.BinaryOperator;

@RestController
public class TripAdvisorController {
    //private BinaryOperator<String> binaryOperator = (s1, s2)-> s1 + "|" + s2;

    @Autowired
    private TripAdvisorService tripAdvisorService;
    @GetMapping("/routes/{places}")
    @ResponseBody  List<Way> getBestRouteTrip(@PathVariable String places) {
        return tripAdvisorService.getBestRouteTrip(places);
    }
}
