package com.view.points.mostviews.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Location {
    private String name;
    private int number;
    private final LocalTime closingTime;
    private final LocalTime openingTime;
    private final String address;
    private LocalTime endView;

    @JsonIgnore
    private final List<Way> ways = new ArrayList();
    private Integer processed = 0;
    private Integer duration = 1;

    public Location(int number, String name, String address) {
        this.number=number;
        this.name=name;
        this.address = address;


        closingTime =  LocalTime.of(0,0);
        openingTime =  LocalTime.of(0,0);
    }

    public Location(int number, String name, LocalTime openingTime, LocalTime closingTime, String address) {
        this.address = address;

        this.number=number;
        this.name=name;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public void addWay(Way way) {
        ways.add(way);
    }

    public List<Way> getWays() {
        return ways;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }




    public Integer getProcessed() {
        return processed;
    }

    public void setProcessed(Integer processed) {
        this.processed = processed;
    }

    @Override
    public String toString() {
        return number + "-" + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalTime getEndView() {
        return endView;
    }

    public void setEndView(LocalTime endView) {
        this.endView = endView;
    }
}
