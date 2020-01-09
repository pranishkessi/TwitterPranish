package com.si.twitterpranish.model;

public class Tweets {
    private String tweet, image, name,username,user_image;

    public Tweets(String tweet, String image, String name, String username, String user_image) {
        this.tweet = tweet;
        this.image = image;
        this.name = name;
        this.username = username;
        this.user_image = user_image;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }
}
