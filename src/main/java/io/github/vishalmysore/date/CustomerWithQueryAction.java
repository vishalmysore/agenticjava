package io.github.vishalmysore.date;
import com.t4a.annotations.Action;
import com.t4a.annotations.Agent;
import com.t4a.annotations.Prompt;
import io.github.vishalmysore.domain.Customer;

import java.util.Date;

/**
 * Agent to handle customer support queries with date prompt as you can see it will automtically convert he date into Date object in format yyyy-MM-dd
 */
@Agent(groupName = "customer support", groupDescription = "actions related to customer support")
public class CustomerWithQueryAction {

    @Action(description = "Customer has problem create ticket for him")
    public String customerComplaintWithNameandFullInfo(Customer customer, @Prompt(dateFormat = "yyyy-MM-dd") Date dateOfComp , @Prompt(describe = "this is customer complaint") String query) {
        return customer.toString();
    }
}
