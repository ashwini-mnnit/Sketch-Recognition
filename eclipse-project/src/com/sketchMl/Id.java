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
public class Id {
    private final UUID id;
    
    public Id (UUID id) {
        this.id = id;
    }
    public Id()
    {
        this.id = UUID.randomUUID();
    }
    
    public final UUID getId()
    {
        return this.id;
    }
}
