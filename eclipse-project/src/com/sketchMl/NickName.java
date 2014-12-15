/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sketchMl;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author shirsing
 */
@XmlRootElement
public class NickName {
    private String nickname;
    
    public NickName(String nickname) {
        this.nickname = nickname;
    }
    
    public NickName() {
    	nickname = "None";
    }
    
    public void setNickname (String nickname) {
        this.nickname = nickname;
    }
    
    public String getNickname() {
        return this.nickname;
    }
}
