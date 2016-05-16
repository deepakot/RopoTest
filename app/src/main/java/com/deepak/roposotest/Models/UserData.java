package com.deepak.roposotest.Models;

/**
 * Created by Sharma on 5/16/2016.
 */
public class UserData {
    private String about;
    private String id;
    private String username;
    private int followers;
    private int following;
    private String image;
    private String url;
    private String handle;
    private boolean is_following;
    private long createdOn;

    public UserData() {
    }

    public UserData(String about, String id, String username, int followers, int following, String image, String url, String handle, boolean is_following, long createdOn) {
        this.about = about;
        this.id = id;
        this.username = username;
        this.followers = followers;
        this.following = following;
        this.image = image;
        this.url = url;
        this.handle = handle;
        this.is_following = is_following;
        this.createdOn = createdOn;
    }

    public long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    public boolean is_following() {
        return is_following;
    }

    public void setIs_following(boolean is_following) {
        this.is_following = is_following;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
