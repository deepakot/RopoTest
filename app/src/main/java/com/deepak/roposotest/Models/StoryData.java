package com.deepak.roposotest.Models;

/**
 * Created by Sharma on 5/16/2016.
 */
public class StoryData {
    private String description;
    private String id;
    private String url;
    private String verb;
    private String db;
    private String si;
    private String type;
    private String title;
    private boolean like_flag;
    private int likes_count;
    private int comment_count;
    private boolean is_following;
    private int[] liked,comment,commented;
    public StoryData() {
    }

    public StoryData(String description, String id, String url, String verb, String db, String si, String type, String title, boolean like_flag, int likes_count, int comment_count,boolean is_following) {
        this.description = description;
        this.id = id;
        this.verb = verb;
        this.db = db;
        this.si = si;
        this.type = type;
        this.url = url;
        this.title = title;
        this.like_flag = like_flag;
        this.likes_count = likes_count;
        this.comment_count = comment_count;
        this.is_following = is_following;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getSi() {
        return si;
    }

    public void setSi(String si) {
        this.si = si;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isLike_flag() {
        return like_flag;
    }

    public void setLike_flag(boolean like_flag) {
        this.like_flag = like_flag;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public boolean is_following() {
        return is_following;
    }

    public void setIs_following(boolean is_following) {
        this.is_following = is_following;
    }

    public int[] getLiked() {
        return liked;
    }

    public void setLiked(int[] liked) {
        this.liked = liked;
    }

    public int[] getComment() {
        return comment;
    }

    public void setComment(int[] comment) {
        this.comment = comment;
    }

    public int[] getCommented() {
        return commented;
    }

    public void setCommented(int[] commented) {
        this.commented = commented;
    }
}
