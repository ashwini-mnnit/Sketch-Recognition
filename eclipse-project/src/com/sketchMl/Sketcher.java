/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sketchMl;

import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
 *
 * @author shirsing
 */
@XmlRootElement
public class Sketcher {
    private Id id;
    private Dpi dpi;
    private NickName nickname;

    public Sketcher() {
    	id = new Id();
    	dpi = new Dpi();
    	nickname = new NickName();
    }
    
    public Sketcher(UUID id, float XAxis, float YAxis, String nickName) {
        this.id = new Id(id);
        this.dpi = new Dpi(XAxis, YAxis);
        this.nickname = new NickName(nickName);
    }

    public Dpi getDpi() {
        return this.dpi;
    }

    public Id getId() {
        return this.id;
    }

    public NickName getNickName() {
        return this.nickname;
    }
@XmlElement
    public void setDpi(Dpi dpi) {
        this.dpi = dpi;
    }
@XmlElement
    public void setId(Id id) {
        this.id = id;
    }
@XmlElement
    public void setNickName(NickName nickName) {
        this.nickname = nickName;
    }
    
}
