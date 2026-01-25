package io.github.vishalmysore.map;

import com.t4a.predict.PredictionLoader;
import com.t4a.processor.AIProcessingException;
import com.t4a.processor.AIProcessor;
import io.swagger.models.auth.In;
import lombok.extern.java.Log;

import java.util.Map;

/**
 * Example demonstrating how to use AIProcessor to generate a Map<Integer, String>
 * from a natural language input.
 * INFO: Resulting Map: {1=Cricket, 2=Chess, 3=Soccer}
 */
@Log
public class MapExample {
    public static void main(String[] args) {
        System.setProperty("tools4ai.properties.path", "io/github/vishalmysore/map/tools4ai.properties");
        AIProcessor processor = PredictionLoader.getInstance().createOrGetAIProcessor();
        log.info("AIProcessor initialized: " + (processor != null));
        try {

            Map<Integer,String> sportsMap = (Map<Integer, String>) processor.processSingleAction("Vishal like cricket its number one, then comes soccer which is number 3 , since in between he likes chess as well  ");
            log.info("Resulting Map: " + sportsMap);


        } catch (AIProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
