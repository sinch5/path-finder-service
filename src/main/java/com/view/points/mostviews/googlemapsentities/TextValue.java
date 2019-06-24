package com.view.points.mostviews.googlemapsentities;

public class TextValue {
    private Long value;
    private String text;

    public TextValue() {
    }

    public TextValue(Long value, String text) {
        this.value = value;
        this.text = text;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
