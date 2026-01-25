package io.github.vishalmysore.series;

import com.t4a.annotations.Action;
import com.t4a.annotations.Agent;
import lombok.extern.java.Log;

@Log
@Agent(groupName = "Book Restaruant", groupDescription = "Book restarurant for person with fodd prefernce")
public class BookRestarant {
    @Action
    public String bookRestarant(String personName, String foodPreference) {
        log.info("Booking restarant for " + personName + " with food preference: " + foodPreference);
        // Simulate booking logic
        return "Restarant booked for " + personName + " with food preference: " + foodPreference;
    }
}
