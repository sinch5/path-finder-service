package com.view.points.mostviews.services;

import com.view.points.mostviews.googlemapsentities.DirectMatrixResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface DistanceMatrixService {
    Mono<DirectMatrixResponse> buildMatrix(String locations);
}
