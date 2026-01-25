package io.github.vishalmysore.list;

import com.t4a.predict.PredictionLoader;
import com.t4a.processor.AIProcessingException;
import com.t4a.processor.AIProcessor;
import lombok.extern.java.Log;

import java.util.List;
import java.util.Map;

/**
 * Framework will convert this to json
 * {
 *     "methodName": "myTaskList",
 *     "parameters": [{
 *         "fieldName": "arg0",
 *         "name": "arg0",
 *         "description": "there could be multiple String",
 *         "className": "java.lang.String",
 *         "fieldType": "list",
 *         "fieldValue": ["go to gym", "play football", "watch a cricket match"]
 *     }],
 *     "returnType": "List"
 * }
 * and them map to the action
 *
 * Jan 25, 2026 3:50:32 P.M. io.github.vishalmysore.list.TaskListExample main
 * INFO: Task: go to gym
 * Jan 25, 2026 3:50:32 P.M. io.github.vishalmysore.list.TaskListExample main
 * INFO: Task: play football
 * Jan 25, 2026 3:50:32 P.M. io.github.vishalmysore.list.TaskListExample main
 * INFO: Task: watch a cricket match
 */
@Log
public class TaskListExample {

    public static void main(String[] args) {
        System.setProperty("tools4ai.properties.path", "io/github/vishalmysore/list/tools4ai.properties");
        AIProcessor processor = PredictionLoader.getInstance().createOrGetAIProcessor();
        log.info("AIProcessor initialized: " + (processor != null));
        try {

            String[] taskList = (String[]) processor.processSingleAction("Vishal would like to go to gym , then play football and finally watch a cricket match. ");
           //List<String> taskList = (List<String>) processor.processSingleAction("Vishal would like to one tow and three");
           for (String task : taskList) {
               log.info("Task: " + task);
           }


        } catch (AIProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
