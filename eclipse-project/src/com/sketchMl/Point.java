/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sketchMl;
import java.util.UUID;

/**
 *
 * @author shirsing
 */
public class Point {
    private float xCordinate;
    private float yCordinate;
    private float pressure;
    private String pointName;
    private UUID pointID;
    private long time;

    public Point(float xCordinate, float yCordinate, float pressure, 
            String pointName, UUID pointID, long time) {
        this.xCordinate = xCordinate;
        this.yCordinate = yCordinate;
        this.pressure = pressure;
        this.pointName = pointName;
        this.pointID = pointID;
        this.time = time;
    }

    public Point() {
    
    }

    public UUID getPointID() {
        return pointID;
    }

    public String getPointName() {
        return pointName;
    }

    public float getPressure() {
        return pressure;
    }

    public long getTime() {
        return time;
    }

    public float getXCordinate() {
        return xCordinate;
    }

    public float getYCordinate() {
        return yCordinate;
    }

    public void setPointID(UUID pointID) {
        this.pointID = pointID;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setXCordinate(float xCordinate) {
        this.xCordinate = xCordinate;
    }

    public void setYCordinate(float yCordinate) {
        this.yCordinate = yCordinate;
    }
    
}
