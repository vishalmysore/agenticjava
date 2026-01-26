package io.github.vishalmysore.loop;

import com.t4a.annotations.Action;
import com.t4a.annotations.Agent;
import com.t4a.detect.ActionState;
import com.t4a.processor.ActionCallbackAware;
import lombok.extern.java.Log;

@Log
@Agent(groupName = "Super store marketplace", groupDescription = "Agent to order food items from a superstore")
public class OrderFoodFromSuperStore implements ActionCallbackAware {

    @Action(description = "Order food items from a superstore")
    public Boolean orderFoodFromStore(String[] items) {
        // Simulate ordering food from a superstore
        log.info("Ordering the following items from the superstore:");
        getCallback().sendtStatus("Ordering food items...", ActionState.WORKING);
        for (String item : items) {
            log.info("- " + item);
            getCallback().sendtStatus("Ordered..."+item, ActionState.WORKING);
        }
        getCallback().sendtStatus("Ordering food items...", ActionState.COMPLETED);
        // In a real implementation, this method would interact with an API or service
        // to place the order. Here, we simply return true to indicate success.
        return true;
    }
}
