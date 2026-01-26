package io.github.vishalmysore.loop;

import com.t4a.annotations.Action;
import com.t4a.annotations.Agent;
import com.t4a.detect.ActionState;
import com.t4a.processor.AIProcessingException;
import com.t4a.processor.ActionCallbackAware;
import com.t4a.processor.ProcessorAware;

@Agent(groupName = "food prepare actions",groupDescription = "Actions related to food preparation")
public class FoodPrepareAction implements ProcessorAware, ActionCallbackAware {

    @Action(description = "Prepare a fresh salad with given ingredients")
    public String prepareSalad(String[] ingredients) throws AIProcessingException {
        StringBuilder salad = new StringBuilder("Salad prepared with: ");
        getCallback().sendtStatus("Ordering fresh vegetables...", ActionState.SUBMITTED);
        boolean ordered = (boolean)getProcessor().processSingleAction("order fresh vegetables from store carrot, lettuce, cucumber");
        getCallback().sendtStatus("Ordering fresh vegetables...", ActionState.COMPLETED);
        for (String ingredient : ingredients) {
            salad.append(ingredient).append(", ");
        }
        // Remove trailing comma and space
        if (ingredients.length > 0) {
            salad.setLength(salad.length() - 2);
        }
        return salad.toString();
    }
}
