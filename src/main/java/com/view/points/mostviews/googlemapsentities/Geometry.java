package com.view.points.mostviews.googlemapsentities;

public class Geometry {
    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return location.toString();
    }
}