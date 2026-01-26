package io.github.vishalmysore.callback;

import com.t4a.detect.ActionCallback;
import com.t4a.detect.ActionState;
import com.t4a.predict.PredictionLoader;
import com.t4a.processor.AIProcessingException;
import com.t4a.processor.AIProcessor;
import lombok.extern.java.Log;

import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * Action sometimes need to perform long running tasks like database queries, file processing, etc.
 * In such cases, it's important to provide feedback to the AI processor about the current status of
 * the action. This helps in maintaining transparency and allows the AI to make informed decisions
 * based on the progress of the action.
 * The LongRunningCallback class implements the ActionCallback interface to send periodic status updates
 * during the execution of a long-running action.
 * The sendtStatus method is called at various stages of the action's execution to report its current state.
 * This implementation simulates a long-running task by sending multiple status updates with delays in between.
 * Example log output:
 *Jan 25, 2026 8:48:04 A.M. io.github.vishalmysore.callback.LongRunningCallback sendtStatus
 * INFO: LongRunningCallback - Status: Processing customer ID: 007, State: WORKING
 * Jan 25, 2026 8:48:05 A.M. io.github.vishalmysore.callback.LongRunningCallback sendtStatus
 * INFO: LongRunningCallback - Status: Still processing customer ID: 007 (1/5), State: WORKING
 * Jan 25, 2026 8:48:06 A.M. io.github.vishalmysore.callback.LongRunningCallback sendtStatus
 * INFO: LongRunningCallback - Status: Still processing customer ID: 007 (2/5), State: WORKING
 * Jan 25, 2026 8:48:07 A.M. io.github.vishalmysore.callback.LongRunningCallback sendtStatus
 * INFO: LongRunningCallback - Status: Still processing customer ID: 007 (3/5), State: WORKING
 * Jan 25, 2026 8:48:08 A.M. io.github.vishalmysore.callback.LongRunningCallback sendtStatus
 * INFO: LongRunningCallback - Status: Still processing customer ID: 007 (4/5), State: WORKING
 * Jan 25, 2026 8:48:09 A.M. io.github.vishalmysore.callback.LongRunningCallback sendtStatus
 * INFO: LongRunningCallback - Status: Still processing customer ID: 007 (5/5), State: WORKING
 * Jan 25, 2026 8:48:09 A.M. io.github.vishalmysore.callback.DatabaseProcessingAction getCustomerDetailsFromDatabase
 * INFO: Retrieved customer details: {customerId=007, email=jone.doe@doecars.com, name=John Doe}
 * Jan 25, 2026 8:48:09 A.M. io.github.vishalmysore.callback.LongRunningCallback sendtStatus
 * INFO: LongRunningCallback - Status: completed processing customer ID: 007, State: COMPLETED
 */
@Log
public class DatabaseExample {
    public static void main(String[] args) {
        System.setProperty("tools4ai.properties.path", "io/github/vishalmysore/callback/tools4ai.properties");
        AIProcessor processor = PredictionLoader.getInstance().createOrGetAIProcessor();
        log.info("AIProcessor initialized: " + (processor != null));
        try {

            LongRunningCallback callback = new LongRunningCallback();
            AtomicReference<Object> context = new AtomicReference<>();
            context.set("MCP");
            callback.setContext(context);
            Object obj = processor.processSingleAction("can you get me details of customer id 007, name is james bond he is a spy", callback);
            log.info("Final Result: " + obj);

        } catch (AIProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
