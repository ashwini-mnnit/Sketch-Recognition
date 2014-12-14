/*
 * and open the template in the editor.
 */
package com.sketchMl;

import java.util.UUID;

/*
 *
 * @author shirsing
 */

public class Trigger {
    private UUID triggerId;

    public Trigger() {
    	
    }
    
    public Trigger(UUID triggerId) {
        this.triggerId = triggerId;
    }

    public UUID getTriggerId() {
        return triggerId;
    }

    public void setTriggerId(UUID triggerId) {
        this.triggerId = triggerId;
    }
    
    
}

