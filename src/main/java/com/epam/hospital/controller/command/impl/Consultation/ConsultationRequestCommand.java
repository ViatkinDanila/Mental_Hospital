package com.epam.hospital.controller.command.impl.Consultation;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.controller.constant.CommandName;
import com.epam.hospital.controller.constant.Page;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.treatment.Consultation;
import com.epam.hospital.model.treatment.type.CommunicationType;
import com.epam.hospital.model.treatment.type.ConsultationStatus;
import com.epam.hospital.model.user.User;
import com.epam.hospital.service.database.ConsultationService;
import com.epam.hospital.service.database.UserService;
import com.epam.hospital.service.database.impl.ConsultationServiceImpl;
import com.epam.hospital.service.database.impl.UserServiceImpl;
import com.epam.hospital.service.exception.ServiceException;
import com.epam.hospital.util.constant.Attribute;
import com.epam.hospital.util.constant.Parameter;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TESTED
@Slf4j
public class ConsultationRequestCommand implements Command {
    private static final String CONSULTATION_PENDING_STATUS = "PENDING";
    private static final int WAITING_TREATMENT_COURSE_ID = 1;
    private static final String CONSULTATION_REQUEST_PAGE_COMMAND = "MentalHospital?command=" + CommandName.CONSULTATION_REQUEST_PAGE;
    private static final ConsultationService consultationService = ConsultationServiceImpl.getInstance();
    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        //
        //patientCardIdStr можно брать из сессии
        //
        log.info(ParameterExtractor.extractString("isOnline", requestContext));
        String patentCardIdStr = ParameterExtractor.extractString(Attribute.PATIENT_CARD_ID, requestContext);
        int patientCardId = Integer.parseInt(patentCardIdStr);
        String communicationTypeStr = ParameterExtractor.extractString(Parameter.COMMUNICATION_TYPE, requestContext);
        CommunicationType communicationType;
        if (communicationTypeStr != null){
            communicationType = CommunicationType.ONLINE;
        } else {
            communicationType = CommunicationType.FACE_TO_FACE;
        }

        String doctorName = ParameterExtractor.extractString(Parameter.NAME, requestContext);
        List<String> fullName = new ArrayList<String>(Arrays.asList(doctorName.split(" ")));
        User doctor = userService.getUserByFullName(fullName.get(0), fullName.get(1));

        Consultation consultation = new Consultation().builder()
                .communicationType(communicationType)
                .date(ParameterExtractor.extractDate(Parameter.DATE, requestContext))
                .doctorId(doctor.getUserId())
                .status(ConsultationStatus.valueOf(CONSULTATION_PENDING_STATUS))
                .patientId(patientCardId)
                .build();
        consultationService.save(consultation);
        return CommandResult.redirect(CONSULTATION_REQUEST_PAGE_COMMAND);
    }
}
