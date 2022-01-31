package com.epam.hospital;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        String salt = UUID.randomUUID().toString();
        System.out.println(salt);
//        ConnectionPool connectionPool = ConnectionPool.getInstance();
//        //TODO ошибка возможно тут потому что проходит транзакция но ничего в бд не меняется
//        try{
//            connectionPool.init("jdbc:mysql://localhost:3306/mental_hospital?useSSL=false","root","admin", "com.mysql.cj.jdbc.Driver");
//
//        } catch (ConnectionPoolException e){
//
//        }
//        Map<String, Object> requestAttributes = new HashMap<String, Object>();
//        Map<String, Object> sessionAttributes = new HashMap<String, Object>();;
//         Map<String, String[]> requestParameters = new HashMap<String, String[]>();
//         String[] DOCTOR_ID = {"2","a"};
//         String[] CONSULTATION_ID = {"1","a"};
//         String[] INSTRUCTION = {"drink more milk",""};
//         String[] DISEASE_NAME = {"low skill",""};
//         String[] DISEASE_NAME1 = {"SHIZO",""};
//         String[] SYMPTOMS = {"haha slisl mid",""};
//         String[] SYMPTOMS1 = {"cam c coboi razgovarivaet",""};
//         String[] DOSES = {"1",""};
//         String[] DOSES1 = {"2",""};
//         String[] DRUG_NAME = {"gadja",""};
//         String[] DRUG_NAME1 = {"more gadja",""};
//         String[] DESCRIPTIONS = {"two times in a row",""};
//         String[] DESCRIPTIONS1 = {"and one more time",""};
//         String[] PATIENT_CARD_ID = {"1",""};
//         String[] COMMUNICATION_TYPE = {"ONLINE",""};
//         String[] DATE = {"2002-09-11",""};
//
//
//         requestParameters.put(Parameter.DOCTOR_ID, DOCTOR_ID);
//         requestParameters.put(Parameter.CONSULTATION_ID, CONSULTATION_ID);
//         requestParameters.put(Parameter.INSTRUCTION,INSTRUCTION);
//         requestParameters.put(Parameter.DISEASE + "-0",DISEASE_NAME);
//         requestParameters.put(Parameter.DISEASE + "-1",DISEASE_NAME1);
//         requestParameters.put(Parameter.SYMPTOMS + "-0",SYMPTOMS);
//         requestParameters.put(Parameter.SYMPTOMS + "-1",SYMPTOMS1);
//         requestParameters.put(Parameter.DRUG + "-0",DRUG_NAME);
//         requestParameters.put(Parameter.DRUG + "-1",DRUG_NAME1);
//         requestParameters.put(Parameter.DESCRIPTION + "-0",DESCRIPTIONS);
//         requestParameters.put(Parameter.DESCRIPTION + "-1",DESCRIPTIONS1);
//         requestParameters.put(Parameter.DOSE + "-0",DOSES);
//         requestParameters.put(Parameter.DOSE + "-1",DOSES1);
//         requestParameters.put(Parameter.PATIENT_CARD_ID,PATIENT_CARD_ID);
//         requestParameters.put(Parameter.COMMUNICATION_TYPE,COMMUNICATION_TYPE);
//         requestParameters.put(Parameter.DATE,DATE);
//         requestParameters.put(Parameter.DOCTOR_ID,DOCTOR_ID);
//
//        sessionAttributes.put(Parameter.PATIENT_CARD_ID, 1);
//        RequestContext requestContext = new RequestContext(requestAttributes, requestParameters, sessionAttributes, "fuck");
//        ConsultationCompleteCommand consultationCompleteCommand = new ConsultationCompleteCommand();
//        try{
//         consultationCompleteCommand.execute(requestContext);
//        } catch (ServiceException e){
//            System.out.println("I loh");
//        }
    }
}
