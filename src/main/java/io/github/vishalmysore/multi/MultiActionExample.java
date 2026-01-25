package io.github.vishalmysore.multi;

import com.t4a.predict.PredictionLoader;
import com.t4a.processor.AIProcessingException;
import com.t4a.processor.AIProcessor;
import lombok.extern.java.Log;

@Log
public class MultiActionExample {
    public static void main(String[] args) {
        System.setProperty("tools4ai.properties.path", "io/github/vishalmysore/multi/tools4ai.properties");
        AIProcessor processor = PredictionLoader.getInstance().createOrGetAIProcessor();
        log.info("AIProcessor initialized: " + (processor != null));
        try {
            log.info((String) processor.processSingleAction("vishal wants to travel and wants adventure"));
            log.info((String) processor.processSingleAction("vishals computer is broken he needs to fix it "));
        } catch (AIProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
