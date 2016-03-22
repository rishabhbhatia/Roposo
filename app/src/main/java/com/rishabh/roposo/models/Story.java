package com.rishabh.roposo.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rishabh bhatia on 21-03-2016.
 */
public class Story {

    @SerializedName("username")
    private String author;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("image")
    private String profilePhotoUrl;

    @SerializedName("url")
    private String profileUrl;

    @SerializedName("createdOn")
    private Long createdOn;

    @SerializedName("verb")
    private String verb;

    @SerializedName("is_following")
    private boolean isFollowing;

    @SerializedName("si")
    private String contentPhoto;

    public Number getFollowing() {
        return following;
    }

    public void setFollowing(Number following) {
        this.following = following;
    }

    public Number getFollowers() {
        return followers;
    }

    public void setFollowers(Number followers) {
        this.followers = followers;
    }

    @SerializedName("following")
    private Number following;

    @SerializedName("followers")
    private Number followers;


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public boolean isFollowing() {
        return isFollowing;
    }

    public void setFollowing(boolean following) {
        isFollowing = following;
    }

    public Long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Long createdOn) {
        this.createdOn = createdOn;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getContentPhoto() {
        return contentPhoto;
    }

    public void setContentPhoto(String contentPhoto) {
        this.contentPhoto = contentPhoto;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }
}
