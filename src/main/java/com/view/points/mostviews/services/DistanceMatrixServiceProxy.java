package com.view.points.mostviews.services;

import com.view.points.mostviews.googlemapsentities.DirectMatrixResponse;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

import java.util.List;

@FeignClient(name = "google-distance-matrix-api-adapter")
@RibbonClient("google-distance-matrix-api-adapter")
public interface DistanceMatrixServiceProxy {
    @GetMapping("/routes/{places}")
    Mono<DirectMatrixResponse> buildMatrix(@PathVariable String places);
}
