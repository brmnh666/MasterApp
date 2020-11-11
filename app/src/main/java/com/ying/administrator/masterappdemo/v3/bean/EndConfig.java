package com.ying.administrator.masterappdemo.v3.bean;

public class EndConfig {
    private String name;
    private boolean imgOn;
    private boolean textOn;
    private boolean videoOn;

    public EndConfig(String name, boolean imgOn, boolean textOn, boolean videoOn) {
        this.name = name;
        this.imgOn = imgOn;
        this.textOn = textOn;
        this.videoOn = videoOn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImgOn() {
        return imgOn;
    }

    public void setImgOn(boolean imgOn) {
        this.imgOn = imgOn;
    }

    public boolean isTextOn() {
        return textOn;
    }

    public void setTextOn(boolean textOn) {
        this.textOn = textOn;
    }

    public boolean isVideoOn() {
        return videoOn;
    }

    public void setVideoOn(boolean videoOn) {
        this.videoOn = videoOn;
    }
}
