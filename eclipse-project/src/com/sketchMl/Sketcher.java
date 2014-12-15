/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sketchMl;

import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 *
 * @author shirsing
 */
@XmlRootElement
@XmlType(propOrder={"id", "dpi", "nickname" })
public class Sketcher {
    private UUID id;
    private Dpi dpi;
    private String nickname;

    public Sketcher() {
    	id = UUID.randomUUID();
    	dpi = new Dpi();
    	nickname = "none";
    }
    
    public Sketcher(UUID id, float XAxis, float YAxis, String nickname) {
        this.id = id;
        this.dpi = new Dpi(XAxis, YAxis);
        this.nickname = nickname;
    }

	public UUID getId() {
		return id;
	}
	@XmlElement
	public void setId(UUID id) {
		this.id = id;
	}

	public Dpi getDpi() {
		return dpi;
	}
	@XmlElement
	public void setDpi(Dpi dpi) {
		this.dpi = dpi;
	}

	public String getNickname() {
		return nickname;
	}
@XmlElement
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
