/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sketchMl;

import java.util.ArrayList;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author shirsing
 */
@XmlRootElement
public class Edit {
    private Trigger trigger;
    private ArrayList<Arg> arg;
    private UUID id;
    private long time;
    private String type;

    public Edit() {
    	arg = new ArrayList<Arg>();
    	id = UUID.randomUUID();
    	type = "Erase";
    	trigger = new Trigger();
    	time = 1000000;
    }
    
    public Edit(Trigger trigger, ArrayList<Arg> arg, UUID editId, long time, 
            String editType) {
        this.trigger = trigger;
        this.arg = arg;
        this.id = editId;
        this.time = time;
        this.type = editType;
    }

	public Trigger getTrigger() {
		return trigger;
	}
@XmlElement
	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}

	public ArrayList<Arg> getArg() {
		return arg;
	}
	@XmlElement
	public void setArg(ArrayList<Arg> arg) {
		this.arg = arg;
	}

	public UUID getId() {
		return id;
	}
@XmlAttribute
	public void setId(UUID id) {
		this.id = id;
	}

	public long getTime() {
		return time;
	}
	@XmlAttribute
	public void setTime(long time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}
	@XmlAttribute
	public void setType(String type) {
		this.type = type;
	}

    
    
}

