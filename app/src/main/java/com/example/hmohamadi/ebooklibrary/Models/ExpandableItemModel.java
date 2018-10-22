package com.example.hmohamadi.ebooklibrary.Models;

import android.animation.TimeInterpolator;

public class ExpandableItemModel {
    private String title;// ="sample title";
    private TimeInterpolator interpolator;
    private String description;// ="sample description";

    public ExpandableItemModel(String _title,String _description,  TimeInterpolator _interpolator) {
        this.setTitle(_title);
        this.setDescription(_description);
        this.setInterpolator(_interpolator);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TimeInterpolator getInterpolator() {
        return interpolator;
    }

    public void setInterpolator(TimeInterpolator interpolator) {
        this.interpolator = interpolator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
