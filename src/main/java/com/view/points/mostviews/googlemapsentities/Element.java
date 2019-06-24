package com.view.points.mostviews.googlemapsentities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public class Element {
    private TextValue distance;
    private TextValue duration;

    @JsonProperty("duration_in_traffic")
    private Optional<TextValue> duration_in_traffic;
    private String status;

    public TextValue getDistance() {
        return distance;
    }

    public void setDistance(TextValue distance) {
        this.distance = distance;
    }

    public TextValue getDuration() {
        return duration;
    }

    public void setDuration(TextValue duration) {
        this.duration = duration;
    }

    public Optional<TextValue> getDuration_in_traffic() {
        return duration_in_traffic;
    }

    public void setDuration_in_traffic(Optional<TextValue> duration_in_traffic) {
        this.duration_in_traffic = duration_in_traffic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Elements{" +
                "distance=" + distance +
                ", duration=" + duration +
                ", duration_in_traffic=" + duration_in_traffic +
                ", status='" + status + '\'' +
                '}';
    }
}
