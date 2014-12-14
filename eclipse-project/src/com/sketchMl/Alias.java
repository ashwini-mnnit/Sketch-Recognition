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
public class Alias {
    private String aliasType;
    private String aliasName;
    private UUID aliasId;

    public Alias(String aliasType, String aliasName, UUID aliasId) {
        this.aliasType = aliasType;
        this.aliasName = aliasName;
        this.aliasId = aliasId;
    }

    public Alias() {
    
    }

    public UUID getAliasId() {
        return aliasId;
    }

    public String getAliasName() {
        return aliasName;
    }

    public String getAliasType() {
        return aliasType;
    }

    public void setAliasId(UUID aliasId) {
        this.aliasId = aliasId;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public void setAliasType(String aliasType) {
        this.aliasType = aliasType;
    }
    
    
    
    
}
