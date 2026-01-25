package io.github.vishalmysore.date;

import com.t4a.predict.PredictionLoader;
import com.t4a.processor.AIProcessingException;
import com.t4a.processor.AIProcessor;
import lombok.extern.java.Log;

/**
 * INFO: Processing result: Customer(firstName=null, lastName=null, reasonForCalling=मैं अपनी हाल की ऑर्डर के बारे में शिकायत करना चाहता हूँ जो इस साल अमेरिका के स्वतंत्रता दिवस के दौरान आई थी। उत्पाद क्षतिग्रस्त आया और पैकेजिंग Poor थी। इसके अलावा, डिलीवरी में तीन दिन की देरी हुई, जो बहुत असुविधाजनक था। मैं क्षतिग्रस्त वस्तु के लिए धनवापसी या प्रतिस्थापन का अनुरोध करना चाहता हूँ। कृपया मुझे बताएं कि इस शिकायत के साथ आगे कैसे बढ़ें।, location=null, dateJoined=Tue Oct 03 00:00:00 EDT 2023)
 */
@Log
public class CustomerCompaintExample {
    public static void main(String[] args) {
        System.setProperty("tools4ai.properties.path", "io/github/vishalmysore/date/tools4ai.properties");
        AIProcessor processor = PredictionLoader.getInstance().createOrGetAIProcessor();

        log.info("AIProcessor initialized: " + (processor != null));
        try {
          String result  = processor.processSingleAction("I want to complain about my recent order which arrived during American Independence day this year. The product arrived damaged and the packaging was poor. Additionally, the delivery was delayed by three days, which was very inconvenient. I would like to request a refund or a replacement for the damaged item. Please let me know how to proceed with this complaint.").toString();
          log.info("Processing result: " + result);
        } catch (AIProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
