package io.github.vishalmysore.humaninloop;

import com.t4a.detect.FeedbackLoop;
import com.t4a.detect.HumanInLoop;
import com.t4a.predict.PredictionLoader;
import com.t4a.processor.AIProcessingException;
import com.t4a.processor.AIProcessor;
import lombok.extern.java.Log;

import java.util.Map;

/**
 * INFO: Hello! How can I assist you today?
 * io.github.vishalmysore.humaninloop.FoodChoiceExample$1 allow
 * INFO: HumanInLoop invoked for method: whatFoodDoesThisPersonLike with prompt: what does Vishal like to eat and params: {
 *     "methodName": "whatFoodDoesThisPersonLike",
 *     "parameters": [{
 *         "name": "arg0",
 *         "type": "String",
 *         "fieldValue": "Vishal"
 *     }],
 *     "returnType": "String"
 * }
 *  io.github.vishalmysore.humaninloop.SimpleActionWithAllergyCheck whatFoodDoesThisPersonLike
 * INFO: Inside whatFoodDoesThisPersonLike action with name: Vishal
 *  io.github.vishalmysore.humaninloop.FoodChoiceExample main
 * INFO: Response: Paneer Butter Masala
 */

/**
 * This class demonstrates the concept of "Human-in-the-Loop" in AI processing.
 * Human-in-the-Loop refers to a system where human intervention is integrated into
 * automated processes to validate, guide, or override AI decisions. In this example,
 * the HumanInLoop interface is implemented to allow human oversight during AI query
 * processing. The 'allow' method is invoked before the AI generates a response,
 * providing an opportunity for human feedback or validation. Here, it logs the
 * invocation details and automatically approves the AI response by returning a
 * FeedbackLoop that validates the response as true.
 *
 * Real-life use cases:
 * - In a personalized meal planning application, AI might suggest food choices based
 *   on user profiles and historical data. However, human-in-the-loop can be crucial
 *   for handling nuanced factors like sudden dietary changes, allergies, cultural
 *   preferences, or emotional states that AI cannot fully predict. For instance,
 *   if the AI suggests a dish containing nuts, the human-in-the-loop mechanism can
 *   prompt for confirmation or override the suggestion to ensure user safety and satisfaction.
 * - In medical diagnostics, AI could analyze patient data to suggest diagnoses or
 *   treatment plans. Human-in-the-loop ensures that qualified medical professionals
 *   review and validate these suggestions, especially in critical cases where AI
 *   might miss subtle symptoms or context-specific factors, preventing potential
 *   misdiagnoses and ensuring patient safety.
 * - In financial investment systems, AI might recommend trades or portfolio adjustments
 *   based on market data and algorithms. Human-in-the-loop allows financial advisors
 *   to review and approve these recommendations, considering broader economic factors,
 *   risk tolerance, and regulatory compliance that AI might not fully account for,
 *   thereby reducing the risk of significant financial losses.
 */
@Log
public class FoodChoiceExample {
    public static void main(String[] args) {
        System.setProperty("tools4ai.properties.path", "io/github/vishalmysore/humaninloop/tools4ai.properties");
        AIProcessor processor = PredictionLoader.getInstance().createOrGetAIProcessor();
        log.info("AIProcessor initialized: " + (processor != null));
        try {
            log.info(processor.query("Hello, world!"));
            Object obj = processor.processSingleAction("what does Vishal like to eat", new HumanInLoop() {
                @Override
                public FeedbackLoop allow(String promptText, String methodName, Map<String, Object> params) {
                    log.info("HumanInLoop invoked for method: " + methodName + " with prompt: " + promptText + " and params: " + params);
                    return new FeedbackLoop() {
                        @Override
                        public boolean isAIResponseValid() {
                            return true;
                        }
                    };
                }

                @Override
                public FeedbackLoop allow(String promptText, String methodName, String params) {
                    log.info("HumanInLoop invoked for method: " + methodName + " with prompt: " + promptText + " and params: " + params);
                    return new FeedbackLoop() {
                        @Override
                        public boolean isAIResponseValid() {
                            return true;
                        }
                    };
                }
            });
            log.info("Response: " + obj);
        } catch (AIProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}