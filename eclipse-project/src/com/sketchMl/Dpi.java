/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sketchMl;

/**
 *
 * @author shirsing
 */
public class Dpi {
    private float dpiXAxis;
    private float dpiYAxis;
    
    public Dpi (float dpiXAxis, float dpiYAxis) {
        this.dpiXAxis = dpiXAxis;
        this.dpiYAxis = dpiYAxis;
    }
    /*
     * Default constructor
     */
    public Dpi() {
    
    }
    
    public void setDpiXAxis(float dpiXAxis) {
        this.dpiXAxis = dpiXAxis;
    }
    
    public void setDpi(float dpiXAxis, float dpiYAxis) {
        this.dpiXAxis = dpiXAxis;
        this.dpiYAxis = dpiYAxis;
    }
    public void setdpiYAxis(float dpiYAxis) {
        this.dpiYAxis = dpiYAxis;
    }
    
    
    public float getDpiXAxis() {
        return this.dpiXAxis;
    }
    
    public float getDpiYAxis() {
        return this.dpiYAxis;
    }
}
