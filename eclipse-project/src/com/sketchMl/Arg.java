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
public class Arg {
 private String argType;
 private UUID argId;

    public Arg(String argType, UUID argId) {
        this.argType = argType;
        this.argId = argId;
    }

    public Arg() {
    
    }

    public UUID getArgId() {
        return argId;
    }

    public String getArgType() {
        return argType;
    }

    public void setArgId(UUID argId) {
        this.argId = argId;
    }

    public void setArgType(String argType) {
        this.argType = argType;
    }
     
 
}
