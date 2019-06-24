package com.view.points.mostviews.entities;

import java.util.List;

public class PlaceResponse {
    private List<Candidate> candidates;
    private String status;

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
