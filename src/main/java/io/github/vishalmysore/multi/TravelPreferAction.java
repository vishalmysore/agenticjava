package io.github.vishalmysore.multi;

import com.t4a.annotations.Action;
import com.t4a.annotations.Agent;
import com.t4a.annotations.Prompt;
import lombok.extern.java.Log;

@Log
@Agent(groupName = "TravelPreferAction", groupDescription = "Handles travel preference actions")
public class TravelPreferAction {

    @Action(description = "Handles travel preferences based on nameOfPersonTravelling and preferenceOrTypeWanted type")
    public String preferTravel(String nameOfPersonTravelling , @Prompt(describe = "just the type of travel") String preferenceOrTypeWanted) {
        log.info("TravelPreferAction invoked with nameOfPersonTravelling: " + nameOfPersonTravelling + " and preferenceOrTypeWanted: " + preferenceOrTypeWanted);
        // Logic to handle travel preferenceOrTypeWanted
        if ("vishal".equalsIgnoreCase(nameOfPersonTravelling) && ("adventure".equalsIgnoreCase(preferenceOrTypeWanted) || "leisure".equalsIgnoreCase(preferenceOrTypeWanted))) {
            return "Vishal prefers to travel to arizona ";
        } else if ("vinod".equalsIgnoreCase(nameOfPersonTravelling) && ("cultural".equalsIgnoreCase(preferenceOrTypeWanted) || "historical".equalsIgnoreCase(preferenceOrTypeWanted))) {
            return "Vinod prefers to travel to europe ";
        } else if ("anita".equalsIgnoreCase(nameOfPersonTravelling) && ("beach".equalsIgnoreCase(preferenceOrTypeWanted) || "relaxation".equalsIgnoreCase(preferenceOrTypeWanted))) {
            return "Anita prefers to travel to maldives ";
        } else if ("raj".equalsIgnoreCase(nameOfPersonTravelling) && ("wildlife".equalsIgnoreCase(preferenceOrTypeWanted) || "nature".equalsIgnoreCase(preferenceOrTypeWanted))) {
            return "Raj prefers to travel to africa ";
        } else if ("meera".equalsIgnoreCase(nameOfPersonTravelling) && ("urban".equalsIgnoreCase(preferenceOrTypeWanted) || "city".equalsIgnoreCase(preferenceOrTypeWanted))) {
            return "Meera prefers to travel to new york ";
        } else {
            if ("sunita".equalsIgnoreCase(nameOfPersonTravelling) && ("spiritual".equalsIgnoreCase(preferenceOrTypeWanted) || "wellness".equalsIgnoreCase(preferenceOrTypeWanted))) {
                return "Sunita prefers to travel to bali ";
            } else if ("vishal".equalsIgnoreCase(nameOfPersonTravelling) && ("road trip".equalsIgnoreCase(preferenceOrTypeWanted) || "scenic".equalsIgnoreCase(preferenceOrTypeWanted))) {
                return "Vishal prefers to travel to california ";
            } else {
                return "No specific travel preferenceOrTypeWanted found for " + nameOfPersonTravelling + " with preferenceOrTypeWanted " + preferenceOrTypeWanted;
            }
        }
    }
}
