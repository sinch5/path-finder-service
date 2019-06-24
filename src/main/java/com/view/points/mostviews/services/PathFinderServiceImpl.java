package com.view.points.mostviews.services;

import com.view.points.mostviews.model.Location;
import com.view.points.mostviews.model.Way;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PathFinderServiceImpl implements PathFinderService {

    public List<Way> calculateRoute(Location source, List<Integer> vertex) {
        LocalTime startTime = LocalTime.of(11,0,0);
        List<Way> ways = new ArrayList();
        Location location = source;
        for (Integer vert:vertex) {
            for (Way way : location.getWays()) {
                if (way.getDestination().getNumber().equals(vert)) {
                    LocalTime arrivalTime = startTime.plusSeconds(way.getPathLengthInTime());
                    if (arrivalTime.compareTo(way.getDestination().getOpeningTime()) == -1) {
                        arrivalTime = way.getDestination().getOpeningTime();
                    }
                    LocalTime endViewing = arrivalTime.plusSeconds(way.getDestination().getDuration());
                    if (endViewing.compareTo(way.getDestination().getClosingTime()) < 1 || LocalTime.of(0,0).equals(way.getDestination().getClosingTime())) {
                        ways.add(way);
                        location = way.getDestination();
                        startTime = endViewing;
                        location.setEndView(endViewing);
                    }
                }
            }
        }
        return  ways;
    }
}
