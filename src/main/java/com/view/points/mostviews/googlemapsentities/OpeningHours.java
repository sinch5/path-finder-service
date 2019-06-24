package com.view.points.mostviews.googlemapsentities;

import java.util.List;

public class OpeningHours {
    private boolean opening_now;
    private List<Period> periods;

    public boolean isOpening_now() {
        return opening_now;
    }

    public void setOpening_now(boolean opening_now) {
        this.opening_now = opening_now;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }
}
