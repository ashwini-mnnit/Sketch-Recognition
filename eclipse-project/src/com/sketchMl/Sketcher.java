/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sketchMl;

import java.util.UUID;

/*
 *
 * @author shirsing
 */

public class Sketcher {
    private Id sketcherId;
    private Dpi dpi;
    private NickName nickName;

    public Sketcher() {
    	
    }
    
    public Sketcher(UUID id, float XAxis, float YAxis, String nickName) {
        this.sketcherId = new Id(id);
        this.dpi = new Dpi(XAxis, YAxis);
        this.nickName = new NickName(nickName);
    }

    public Dpi getDpi() {
        return this.dpi;
    }

    public Id getId() {
        return this.sketcherId;
    }

    public NickName getNickName() {
        return this.nickName;
    }

    public void setDpi(Dpi dpi) {
        this.dpi = dpi;
    }

    public void setId(Id id) {
        this.sketcherId = id;
    }

    public void setNickName(NickName nickName) {
        this.nickName = nickName;
    }
    
}
