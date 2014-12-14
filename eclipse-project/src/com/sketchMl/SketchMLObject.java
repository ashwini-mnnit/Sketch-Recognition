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
public class SketchMLObject {
    
    private final UUID sketchMlObjectId;

    public SketchMLObject(UUID id)
    {
        this.sketchMlObjectId = id;
    }

    public SketchMLObject()
    {
        this.sketchMlObjectId = UUID.randomUUID();
    }
    
    public final UUID getId()
    {
        return this.sketchMlObjectId;
    }
}



