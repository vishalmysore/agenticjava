package io.github.vishalmysore.callback;

import com.t4a.annotations.Action;
import com.t4a.annotations.Agent;
import com.t4a.detect.ActionCallback;
import com.t4a.detect.ActionState;
import com.t4a.processor.ActionCallbackAware;
import lombok.extern.java.Log;

import java.util.Map;

/**
 * An agent that handles database processing actions.
 * This shows the use of ActionCallback to report status during long-running operations.
 * Since the client and action communicatiion is asynchronous the action can send status updates back to the client
 * using the ActionCallback object.
 * you can use the getCallback() method to get the ActionCallback object.
 */
@Log
@Agent(groupName = "database",  groupDescription = "Handles database processing actions" ,prompt = "Handles database processing actions but do not call shutdown or delete data")
public class DatabaseProcessingAction implements ActionCallbackAware {

    @Action
    public Map<String, String> getCustomerDetailsFromDatabase(String customerId , String customerName, String profession) {
        // Simulate database processing

        log.info("Fetching details for customer ID: " + customerId);
        log.info("Customer Name: " + customerName);
        log.info("Profession: " + profession);
        ActionCallback callback = getCallback();
        // Here you would add your database access logic
        Object obj =  callback.getContext();
        log.info("Context Object: " + obj);

        callback.sendtStatus("Processing customer ID: " + customerId,ActionState.WORKING);
        for(int i=0;i<5;i++) {
            try {
                Thread.sleep(1000); // Simulate time-consuming processing
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            callback.sendtStatus("Still processing customer ID: " + customerId + " (" + (i+1) + "/5)",ActionState.WORKING);
        }
        // Simulate retrieved data
        Map<String, String> customerDetails = Map.of(
                "customerId", customerId,
                "name", "John Doe",
                "email", "jone.doe@doecars.com");
        log.info("Retrieved customer details: " + customerDetails);
        callback.sendtStatus("completed processing customer ID: " + customerId,ActionState.COMPLETED);
        return customerDetails;
    }
}
