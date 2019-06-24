package com.view.points.mostviews.services;

import com.view.points.mostviews.model.Location;
import com.view.points.mostviews.model.Way;

import java.util.List;

public interface TripAdvisorService {
    List<Way> getBestRouteTrip(String places);
}

