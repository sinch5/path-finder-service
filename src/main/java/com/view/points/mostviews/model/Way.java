package com.view.points.mostviews.model;

import com.view.points.mostviews.model.Location;

public class Way {
    final Location source;
    final Location destination;
    final Long pathLengthInTime;

    public Way(final Location source, Location destination, Long periodOfTime) {
        this.source = source;
        this.destination = destination;
        this.pathLengthInTime = periodOfTime;
    }

    public Location getDestination() {
        return destination;
    }

    public Long getPathLengthInTime() {
        return pathLengthInTime;
    }

    public Location getSource() {
        return source;
    }

    @Override
    public String toString() {
        return  source + "," +
                destination  ;
    }
}
