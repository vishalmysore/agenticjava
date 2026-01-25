package io.github.vishalmysore.series;

import com.t4a.predict.PredictionLoader;
import com.t4a.processor.AIProcessingException;
import com.t4a.processor.AIProcessor;
import com.t4a.processor.scripts.ScriptCallback;
import com.t4a.processor.scripts.ScriptProcessor;
import com.t4a.processor.scripts.ScriptResult;
import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 *This is a series processor whcih will run the commands one by one
 * INFO: Inside whatFoodDoesThisPersonLike action with name: Vishal
 * Jan 25, 2026 4:36:28 P.M. io.github.vishalmysore.series.SeriesExample$1 lineResult
 * INFO: Paneer Butter Masala
 * Jan 25, 2026 4:36:48 P.M. io.github.vishalmysore.series.BookRestarant bookRestarant
 * INFO: Booking restarant for Paneer Butter Masala with food preference: 7 PM
 * Jan 25, 2026 4:36:54 P.M. io.github.vishalmysore.series.SeriesExample$1 lineResult
 * INFO: Restarant booked for Paneer Butter Masala with food preference: 7 PM
 *
 */
@Log
public class SeriesExample {
    public static void main(String[] args) {
        System.setProperty("tools4ai.properties.path", "io/github/vishalmysore/series/tools4ai.properties");
        AIProcessor processor = PredictionLoader.getInstance().createOrGetAIProcessor();
        ScriptProcessor script = new ScriptProcessor(processor);


        log.info("AIProcessor initialized: " + (processor != null));
        try {
            log.info(processor.query("Hello, world!"));
            String commands = "Vishal wants to eat food  first find his food preferencen \n then find his favorite restaurants and book a table for dinner tonight at 7 PM";
            ScriptCallback callback = new ScriptCallback() {
                @Override
                public String lineResult(String result) {
                    log.info(result);
                    return result;
                }
            };
            ScriptResult resultSetup = new ScriptResult();
            BufferedReader commandsReader = new BufferedReader(new StringReader(commands));
            script.processCommands(callback, commandsReader, resultSetup);

            commands = "Vishal wis hungry he needs to eat something, find his favorite restaurants and book a table for dinner tonight at 7 PM";
            script.processCommands(callback, commands, resultSetup);

        } catch (AIProcessingException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}