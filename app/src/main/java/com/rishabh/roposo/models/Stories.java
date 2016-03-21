package com.rishabh.roposo.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rishabh bhatia on 21-03-2016.
 */
public class Stories {

    @Expose
    private List<Story> stories = new ArrayList<>();

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

}
