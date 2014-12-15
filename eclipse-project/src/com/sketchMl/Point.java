/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sketchMl;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author shirsing
 */
@XmlRootElement
@XmlType(propOrder={"id", "name", "time" , "x", "y"})
public class Point {
    private float x;
    private float y;
    private float pressure;
    private String name;
    private UUID id;
    private long time;

    public Point(float xCordinate, float yCordinate, float pressure, 
            String pointName, UUID pointID, long time) {
        this.x = xCordinate;
        this.y = yCordinate;
        this.pressure = pressure;
        this.name = pointName;
        this.id = pointID;
        this.time = time;
    }

    public Point() {
    	x = 0;
    	y = 0;
    	pressure = 0;
    	name = "";
    	id = UUID.randomUUID();
    	time = 1000000;
    }

	public float getX() {
		return x;
	}
@XmlAttribute
	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}
	@XmlAttribute
	public void setY(float y) {
		this.y = y;
	}

	public float getPressure() {
		return pressure;
	}
	@XmlAttribute
	public void setPressure(float pressure) {
		this.pressure = pressure;
	}

	public String getName() {
		return name;
	}
	@XmlAttribute
	public void setName(String name) {
		this.name = name;
	}

	public UUID getId() {
		return id;
	}
	@XmlAttribute
	public void setId(UUID id) {
		if (id == null) {
			id = UUID.randomUUID();
		}
		this.id = id;
	}

	public long getTime() {
		return time;
	}
	@XmlAttribute
	public void setTime(long time) {
		this.time = time;
	}

   
}
