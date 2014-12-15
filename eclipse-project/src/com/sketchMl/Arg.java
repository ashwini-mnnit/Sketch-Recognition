/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sketchMl;

import java.util.UUID;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author shirsing
 */
@XmlRootElement
public class Arg {
 private String type;
 private UUID id;

    public Arg(String argType, UUID argId) {
        this.type = argType;
        this.id = argId;
    }

    public Arg() {
    	id = UUID.randomUUID();
    	type = "Point";
    }

	public String getType() {
		return type;
	}
@XmlAttribute
	public void setType(String type) {
		this.type = type;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}

    
 
}
