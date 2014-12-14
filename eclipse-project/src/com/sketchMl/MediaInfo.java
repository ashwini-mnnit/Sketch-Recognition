/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sketchMl;

/**
 *
 * @author shirsing
 */
public class MediaInfo {
    private Id mediaInfoId;
    private Arg arg;
    private long startTime;
    private String mediaType;

    public MediaInfo() {
    	
    }
    
    public MediaInfo(Id mediaInfoId, Arg arg, long startTime, String mediaType) {
        this.mediaInfoId = mediaInfoId;
        this.arg = arg;
        this.startTime = startTime;
        this.mediaType = mediaType;
    }

    public Arg getArg() {
        return arg;
    }

    public Id getMediaInfoId() {
        return mediaInfoId;
    }

    public String getMediaType() {
        return mediaType;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setArg(Arg arg) {
        this.arg = arg;
    }

    public void setMediaInfoId(Id mediaInfoId) {
        this.mediaInfoId = mediaInfoId;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    
}
