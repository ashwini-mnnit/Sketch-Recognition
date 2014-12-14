/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sketchMl;

import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author shirsing
 */
public class Edit {
    private Trigger trigger;
    private ArrayList<Arg> arg;
    private UUID editId;
    private long time;
    private String editType;

    public Edit() {
    	arg = new ArrayList<Arg>();
    }
    
    public Edit(Trigger trigger, ArrayList<Arg> arg, UUID editId, long time, 
            String editType) {
        this.trigger = trigger;
        this.arg = arg;
        this.editId = editId;
        this.time = time;
        this.editType = editType;
    }

    public ArrayList<Arg> getArg() {
        return arg;
    }

    public UUID getEditId() {
        return editId;
    }

    public String getEditType() {
        return editType;
    }

    public long getTime() {
        return time;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setArg(ArrayList<Arg> arg) {
        this.arg = arg;
    }

    public void setEditId(UUID editId) {
        this.editId = editId;
    }

    public void setEditType(String editType) {
        this.editType = editType;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }
    
}

