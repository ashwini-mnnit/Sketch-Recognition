/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sketchMl;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author shirsing
 */
@XmlRootElement
public class Dpi {
    private float x;
    private float y;
    
    public Dpi (float dpiXAxis, float dpiYAxis) {
        this.x = dpiXAxis;
        this.y = dpiYAxis;
    }
    /*
     * Default constructor
     */
    public Dpi() {
    	x = 0;
    	y = 0;
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
    
}
