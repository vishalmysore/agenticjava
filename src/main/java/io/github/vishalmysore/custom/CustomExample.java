package io.github.vishalmysore.custom;

import com.t4a.predict.PredictionLoader;
import com.t4a.processor.AIProcessingException;
import com.t4a.processor.AIProcessor;
import lombok.extern.java.Log;

/**
 * CustomExample demonstrates the usage of AIProcessor to handle custom actions.
 * YOu will see that the logs show how the custom actions are processed and the results obtained.
 * Frameowrk converts to json
 * ```json
 * {
 *     "methodName": "addOrganization",
 *     "parameters": [{
 *         "name": "arg0",
 *         "fields": [
 *             {
 *                 "fieldName": "name",
 *                 "fieldType": "String",
 *                 "fieldValue": "Juugle"
 *             },
 *             {
 *                 "fieldName": "em",
 *                 "className": "io.github.vishalmysore.domain.Employee",
 *                 "fieldType": "list",
 *                 "prompt": "If you find more than 1 Employee add it as another object inside fieldValue",
 *                 "fieldValue": [{
 *                     "fieldName": "em",
 *                     "className": "io.github.vishalmysore.domain.Employee",
 *                     "fields": [
 *                         {
 *                             "fieldName": "name",
 *                             "fieldType": "String",
 *                             "fieldValue": "Sunil Dutta"
 *                         },
 *                         {
 *                             "fieldName": "department",
 *                             "fieldType": "String",
 *                             "fieldValue": "AI Research"
 *                         },
 *                         {
 *                             "fieldName": "salary",
 *                             "fieldType": "double",
 *                             "fieldValue": 80000.00
 *                         },
 *                         {
 *                             "fieldName": "location",
 *                             "fieldType": "String",
 *                             "fieldValue": "Remote"
 *                         },
 *                         {
 *                             "fieldName": "dateJoined",
 *                             "dateFormat": "ddMMyyyy",
 *                             "description": "convert to actual date",
 *                             "fieldType": "Date",
 *                             "fieldValue": "01012023"
 *                         },
 *                         {
 *                             "fieldName": "tasks",
 *                             "isArray": true,
 *                             "fieldType": "String[]",
 *                             "fieldValue": ["Research", "Development", "Testing"]
 *                         }
 *                     ],
 *                     "fieldType": "io.github.vishalmysore.domain.Employee"
 *                 }]
 *             },
 *             {
 *                 "fieldName": "locations",
 *                 "description": "there could be multiple String",
 *                 "className": "java.lang.String",
 *                 "fieldType": "list",
 *                 "fieldValue": ["Remote", "Headquarters"]
 *             },
 *             {
 *                 "fieldName": "customers",
 *                 "isArray": true,
 *                 "className": "io.github.vishalmysore.domain.Customer",
 *                 "type": "Customer[]",
 *                 "fieldValue": [{
 *                     "className": "io.github.vishalmysore.domain.Customer",
 *                     "fields": [
 *                         {
 *                             "fieldName": "firstName",
 *                             "fieldType": "String",
 *                             "fieldValue": "Ravi"
 *                         },
 *                         {
 *                             "fieldName": "lastName",
 *                             "fieldType": "String",
 *                             "fieldValue": "Sharma"
 *                         },
 *                         {
 *                             "fieldName": "reasonForCalling",
 *                             "description": "convert this to Hindi",
 *                             "fieldType": "String",
 *                             "fieldValue": "सहायता की आवश्यकता"
 *                         },
 *                         {
 *                             "fieldName": "dateJoined",
 *                             "dateFormat": "yyyy-MM-dd",
 *                             "description": "if you dont find date provide todays date in fieldValue",
 *                             "fieldType": "Date",
 *                             "fieldValue": "2023-10-01"
 *                         }
 *                     ]
 *                 }],
 *                 "prompt": "If you find more than 1 Customer add it as another object inside fieldValue",
 *                 "fieldType": "Customer[]"
 *             }
 *         ],
 *         "type": "io.github.vishalmysore.domain.Organization"
 *     }],
 *     "returnType": "Organization"
 * }
 * ```
 *
 * INFO: Organization(name=Juugle, em=[Employee(name=Sunil Dutta, id=0, department=AI Research, salary=80000.0, location=Remote, dateJoined=Sun Jan 01 00:00:00 EST 2023, tasks=[Research AI algorithms, Develop AI models, Collaborate with teams])], locations=[Remote, Headquarters], customers=[Customer(firstName=Rajesh, lastName=Kumar, reasonForCalling=सहायता के लिए कॉल कर रहे हैं, location=null, dateJoined=Sun Oct 01 00:00:00 EDT 2023)])
 * Jan 25, 2026 1:35:47 P.M. io.github.vishalmysore.custom.CustomExample main
 * INFO: Organization(name=Juugle, em=[Employee(name=Sunil Dutta, id=0, department=AI Research, salary=80000.0, location=Remote, dateJoined=Sun Jan 01 00:00:00 EST 2023, tasks=[Research AI algorithms, Develop AI models, Collaborate with teams])], locations=[Remote, Headquarters], customers=[Customer(firstName=Rajesh, lastName=Kumar, reasonForCalling=सहायता के लिए कॉल कर रहे हैं, location=null, dateJoined=Sun Oct 01 00:00:00 EDT 2023)])
 * Jan 25, 2026 1:36:04 P.M. io.github.vishalmysore.custom.MyDiaryAction buildMyDiary
 * INFO: MyDiary(allTheDatesOfAppointment=[Tue Jun 25 00:00:00 EDT 2024], friendsNames=[Sunil Dutta], customer=Customer(firstName=null, lastName=null, reasonForCalling=null, location=null, dateJoined=null), employee=Employee(name=Vishal, id=0, department=null, salary=0.0, location=null, dateJoined=null, tasks=[Schedule meeting with Sunil Dutta]))
 * Jan 25, 2026 1:36:04 P.M. io.github.vishalmysore.custom.CustomExample main
 * INFO: MyDiary(allTheDatesOfAppointment=[Tue Jun 25 00:00:00 EDT 2024], friendsNames=[Sunil Dutta], customer=Customer(firstName=null, lastName=null, reasonForCalling=null, location=null, dateJoined=null), employee=Employee(name=Vishal, id=0, department=null, salary=0.0, location=null, dateJoined=null, tasks=[Schedule meeting with Sunil Dutta]))
 *
 *
 */
@Log
public class CustomExample {
    public static void main(String[] args) {
        System.setProperty("tools4ai.properties.path", "io/github/vishalmysore/custom/tools4ai.properties");
        AIProcessor processor = PredictionLoader.getInstance().createOrGetAIProcessor();
        log.info("AIProcessor initialized: " + (processor != null));
        try {
            //Organization(name=Juugle, em=[Employee(name=Sunil Dutta, id=0, department=AI Research, salary=80000.0, location=Remote, dateJoined=Sun Jan 01 00:00:00 EST 2023, tasks=[Research AI algorithms, Develop AI models, Collaborate with teams])], locations=[Remote, Headquarters], customers=[Customer(firstName=Rajesh, lastName=Kumar, reasonForCalling=सहायता के लिए कॉल कर रहे हैं, location=null, dateJoined=Sun Oct 01 00:00:00 EDT 2023)])
            log.info(processor.processSingleAction("Sunil Dutta is a software engineer at Juugle working on AI research add him in the to the company ").toString());
            //MyDiary(allTheDatesOfAppointment=[Tue Jun 25 00:00:00 EDT 2024], friendsNames=[Sunil Dutta], customer=Customer(firstName=null, lastName=null, reasonForCalling=null, location=null, dateJoined=null), employee=Employee(name=Vishal, id=0, department=null, salary=0.0, location=null, dateJoined=null, tasks=[Schedule meeting with Sunil Dutta]))
            log.info(processor.processSingleAction("vishal has a meeting with sunil dutta schedule it on 25th june 2024 at 10 am and send invite to sunil dutta email id, he is going to cricket match after work").toString());
        } catch (AIProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
