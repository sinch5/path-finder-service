package com.view.points.mostviews.services;

import com.view.points.mostviews.googlemapsentities.PlaceDetails;
import reactor.core.publisher.Mono;

public interface PlaceDetailsService {
    Mono<PlaceDetails> findById(String name);
}
