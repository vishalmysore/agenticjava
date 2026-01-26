package io.github.vishalmysore.loop;

import com.t4a.api.ActionGroup;
import com.t4a.api.ActionList;
import com.t4a.predict.PredictionLoader;
import com.t4a.processor.AIProcessingException;
import com.t4a.processor.AIProcessor;

import lombok.extern.java.Log;

import java.util.concurrent.atomic.AtomicReference;

/**
 * The ActionLoopExample demonstrates how Tools4AI can create an action loop or chain based on NLP (Natural Language Processing) decisions, where one action dynamically triggers another. Here's a breakdown:
 *
 * How It Works
 * Initial Query Processing: The AIProcessor receives the query "i need to eat fruit salad" and uses NLP to predict/select the first action, likely FoodPrepareAction (an @Action-annotated method in an @Agent class).
 *
 * Action Execution with Callback:
 *
 * MultiActionCallback (similar to LongRunningCallback) is passed to processSingleAction.
 * During execution, FoodPrepareAction runs and can send status updates via sendtStatus (e.g., "Preparing fruit salad").
 * The context (AtomicReference<Object> set to "MCP") can share data between actions, such as ingredient lists or user preferences.
 * Loop/Chain Trigger:
 *
 * If FoodPrepareAction determines it needs ingredients (e.g., based on inventory checks), it can internally call another action, like an OrderIngredientsAction, via the processor or callback.
 * This creates a loop: Action 1 → Decision → Action 2 → etc., until completion.
 * The callback reports progress across the chain, ensuring thread-safety if actions run concurrently.
 * Result: The final result (e.g., "Fruit salad prepared with ordered ingredients") is logged.
 *
 * Key Benefits
 * Dynamic Workflow: NLP allows flexible, decision-based action sequences without hardcoding.
 * Transparency: Callbacks provide real-time updates (e.g., "Ordering apples", "Salad ready").
 * Thread-Safety: With AtomicReference in the callback, shared context remains safe in multi-threaded loops.
 *
 */

@Log
public class ActionLoopExample {
    public static void main(String[] args) {
        System.setProperty("tools4ai.properties.path", "io/github/vishalmysore/loop/tools4ai.properties");
        AIProcessor processor = PredictionLoader.getInstance().createOrGetAIProcessor();
        ActionList listOfActions =  PredictionLoader.getInstance().getActionGroupList();
        listOfActions.getGroupActions();
        log.info("AIProcessor initialized: " + (processor != null));
        try {

            MultiActionCallback callback = new MultiActionCallback();
            AtomicReference<Object> context = new AtomicReference<>();
            context.set("MCP");
            callback.setContext(context);
            Object obj = processor.processSingleAction("i need to eat fruit salad", callback);
            log.info("Final Result: " + obj);

        } catch (AIProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
