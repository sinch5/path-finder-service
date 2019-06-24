package com.view.points.mostviews.services;

import com.view.points.mostviews.model.Location;
import com.view.points.mostviews.model.Way;

import java.util.List;

public interface PathFinderService {
    List<Way> calculateRoute(Location source, List<Integer> vertex);
}
