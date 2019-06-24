package com.view.points.mostviews.services;

import com.view.points.mostviews.entities.PlaceResponse;
import reactor.core.publisher.Mono;

public interface PlacesService {
    Mono<PlaceResponse> findByName(String name);
}
