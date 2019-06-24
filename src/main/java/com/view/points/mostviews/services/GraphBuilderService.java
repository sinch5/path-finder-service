package com.view.points.mostviews.services;

import com.view.points.mostviews.model.Location;

import java.util.List;
import java.util.Map;

public interface GraphBuilderService {
    Map<Location, List<Location>> buildGraph(String places);
}
