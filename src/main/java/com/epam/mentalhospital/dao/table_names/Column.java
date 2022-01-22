package com.epam.mentalhospital.dao.table_names;

public class Column {

    public static final String HOSPITALIZATION_ID = "id";
    public static final String HOSPITALIZATION_DAYS_IN = "days_in_hospital";
    public static final String HOSPITALIZATION_CHAMBER_ID = "chambers_id";
    public static final String HOSPITALIZATION_DATE_IN = "hospitalization_date";
    public static final String HOSPITALIZATION_DATE_OUT = "discharge_date";
    public static final String HOSPITALIZATION_PATIENT_ID = "patient_id";

    public static final String DISEASES_ID = "id";
    public static final String DISEASES_NAME = "name";
    public static final String DISEASES_DESCRIPTION = "description";

    public static final String DISEASE_SYMPTOMS_COURSE_ID = "treatment_course_id";
    public static final String DISEASE_SYMPTOMS_DISEASE_ID = "disease_id";
    public static final String DISEASE_SYMPTOMS_SYMPTOMS = "symptoms";

    public static final String DRUGS_ID = "id";
    public static final String DRUGS_NAME = "name";

    public static final String DRUG_RECIPE_DRUG_ID = "drug_id";
    public static final String DRUG_RECIPE_DOSE = "dose";
    public static final String DRUG_RECIPE_DESCRIPTION = "description";
    public static final String DRUG_RECIPE_COURSE_ID = "treatment_course_id";


    public static final String TREATMENT_COURSE_CONSULTATION_ID = "consultation_id";
    public static final String TREATMENT_COURSE_INSTRUCTION = "instructions";
    public static final String TREATMENT_COURSE_DISEASE_ID = "diseases_id";
    public static final String TREATMENT_COURSE_SYMPTOMS = "symptoms";
    public static final String TREATMENT_COURSE_DRUG_ID = "drugs_id";
    public static final String TREATMENT_COURSE_DRUG_DOSE = "dose";
    public static final String TREATMENT_COURSE_DESCRIPTION = "description";
    public static final String TREATMENT_COURSE_ID = "id";

    public static final String CONSULTATION_ID = "id";
    public static final String CONSULTATION_COMMUNICATION_TYPE_ID = "communication_type_id";
    public static final String CONSULTATION_DOCTOR_ID = "doctor_id";
    public static final String CONSULTATION_PATIENT_ID = "patient_id";
    public static final String CONSULTATION_DATE = "date";
    public static final String CONSULTATION_DURATION = "duration";
    public static final String CONSULTATION_PRICE = "price";
    public static final String CONSULTATION_TYPE_ID = "id";
    public static final String CONSULTATION_TYPE_NAME = "name";

    public static final String PATIENT_CARD_ID = "id";
    public static final String PATIENT_CARD_USER_ID = "user_id";
    public static final String PATIENT_CARD_SPARE_NUMBER = "spare_number";
    public static final String PATIENT_CARD_AGE = "age";
    public static final String PATIENT_CARD_SEX = "sex";

    public static final String USER_ID = "id";
    public static final String USER_ROLE_ID = "user_role_id";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASWORD = "password";
    public static final String USER_EMAIL = "email";
    public static final String USER_FIRS_NAME = "first_name";
    public static final String USER_LAST_NAME = "last_name";
    public static final String USER_NUMBER = "number";
    public static final String USER_AGE = "age";
    public static final String USER_ROLES_ID = "id";
    public static final String USER_ROLES_NAME = "name";
    public static final String USER_PROFESSION = "profession";

    public static final String HOSPITAL_ID = "id";
    public static final String HOSPITAL_ADDRESS = "address";
    public static final String HOSPITAL_NUMBER = "number";

    public static final String CHAMBER_ID = "id";
    public static final String CHAMBER_CHAMBER_TYPE_ID = "chambres_type_id";
    public static final String CHAMBER_HOSPITAL_ID = "hospital_id";
    public static final String CHAMBER_NUMBER_OF_FREE_BEDS = "num_free_beds";
    public static final String CHAMBER_NUMBER_OF_AVAILABLE_ROOMS = "num_available_rooms";

    public static final String CHAMBERS_TYPE_ID = "id";
    public static final String CHAMBERS_TYPE_TITLE = "title";
    public static final String CHAMBERS_TYPE_NUMBER_OF_BEDS = "num_of_beds";
    public static final String CHAMBERS_TYPE_PRICE = "price";

}
