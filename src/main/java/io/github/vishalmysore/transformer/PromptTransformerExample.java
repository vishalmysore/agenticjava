package io.github.vishalmysore.transformer;

import com.t4a.predict.PredictionLoader;
import com.t4a.processor.AIProcessingException;
import com.t4a.processor.AIProcessor;
import com.t4a.transform.PromptTransformer;
import io.github.vishalmysore.domain.Organization;
import io.github.vishalmysore.domain.RestaurantPojo;
import lombok.extern.java.Log;

/**
 * This show example of how to use PromptTransformer to transform unstructured text into POJOs
 * So your query can be transformed into structured data like this Organization(name=MovieHits inc, em=[Employee(name=Shahrukh Khan, id=0, department=Acting and Dancing, salary=100.0, location=Toronto, dateJoined=Wed Nov 20 00:00:00 EST 9, tasks=[Acting, Dancing])], locations=[Toronto, Montreal, Bombay], customers=null)
 * or even a complex nested object like this INFO: RestaurantPojo{name='Vishal', numberOfPeople=4, restaurantDetails=RestaurantDetails{name='Maharaj Restaurant', location='Mumbai, India'}, cancel=true, reserveDate='2023-08-15'}
 * You can convert any prompt to pojo as long as the pojo is defined properly with fields matching the prompt
 * you can covert prompt to json as well for example
 * {
 *     "lastName": "Gupta",
 *     "firstName": "Vinod",
 *     "reasonForCalling": "Customer is complaining that his computer is not working.",
 *     "dateJoined": "2008-05-12",
 *     "location": "Toronto"
 * }
 *
 * will be result of this prompt
 * I went to the park yesterday and met someone it was so good to meet an old friend. A customer is complaining that his computer is not working, his name is Vinod Gupta,  and he stays in Toronto he joined on 12 May 2008
 */
@Log
public class PromptTransformerExample {
    public static void main(String[] args) throws AIProcessingException {
        System.setProperty("tools4ai.properties.path", "io/github/vishalmysore/simple/tools4ai.properties");
        PromptTransformer tra = PredictionLoader.getInstance().createOrGetPromptTransformer();
        log.info("This is a prompt transformer example.");
        String promptText  = "can you book a dinner reseration in name of Vishal and his family of 4 at Maharaj restaurant on Indian Independence day and make sure its cancellable";
        log.info(tra.transformIntoPojo(promptText, RestaurantPojo.class.getName(),"","").toString());
        promptText = "Shahrukh Khan works for MovieHits inc and his salary is $ 100  he joined Toronto on Labor day, his tasks are acting and dancing. He also works out of Montreal and Bombay";
        log.info(tra.transformIntoPojo(promptText, Organization.class.getName(),"","").toString());
        String jsonString = "{\"lastName\":\"String\",\"firstName\":\"String\",\"reasonForCalling\":\"String\",\"dateJoined\":\"date\",\"location\":\"String\"}";
        jsonString = tra.transformIntoJson(jsonString,"I went to the park yesterday and met someone it was so good to meet an old friend. A customer is complaining that his computer is not working, his name is Vinod Gupta,  and he stays in Toronto he joined on 12 May 2008", "Customer", "get Customer details");
        log.info("Transformed JSON: " + jsonString);
    }
}
