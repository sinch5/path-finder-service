package com.view.points.mostviews.googlemapsentities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Optional;

public class Result {
    private String formatted_phone_number;
    private String name;

    private Optional<OpeningHours> opening_hours;
    private String formatted_address;
    private Geometry geometry;

    public String getFormatted_phone_number() {
        return formatted_phone_number;
    }

    public void setFormatted_phone_number(String formatted_phone_number) {
        this.formatted_phone_number = formatted_phone_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<OpeningHours> getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(Optional<OpeningHours> opening_hours) {
        this.opening_hours = opening_hours;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
