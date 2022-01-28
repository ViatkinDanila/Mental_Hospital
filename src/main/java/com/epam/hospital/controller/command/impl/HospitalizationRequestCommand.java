package com.epam.hospital.controller.command.impl;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.treatment.ChamberStaying;
import com.epam.hospital.model.treatment.Hospitalization;
import com.epam.hospital.model.treatment.type.HospitalizationStatus;
import com.epam.hospital.service.database.ChamberService;
import com.epam.hospital.service.database.ConsultationService;
import com.epam.hospital.service.database.HospitalizationService;
import com.epam.hospital.service.database.impl.ChamberServiceImpl;
import com.epam.hospital.service.database.impl.ConsultationServiceImpl;
import com.epam.hospital.service.database.impl.HospitalizationServiceImpl;
import com.epam.hospital.service.exception.ServiceException;
import com.epam.hospital.util.constant.Attribute;

public class HospitalizationRequestCommand implements Command {
    private static final int DEFAULT_HOSPITAL_ID = 1;
    private static final String HOSPITALIZATION_PENDING_STATUS = "PENDING";
    private static final HospitalizationService hospitalizationService = HospitalizationServiceImpl.getInstance();
    private static final ChamberService chamberService = ChamberServiceImpl.getInstance();

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        //
        //patientCardIdStr можно брать из сессии
        //
        String patentCardIdStr = ParameterExtractor.extractString(Attribute.PATIENT_CARD_ID, requestContext);
        int patientCardId = Integer.parseInt(patentCardIdStr);
        //TODO
        String chamberTypeIdSet = ParameterExtractor.extractString(Attribute.CHAMBER_ID, requestContext);
        int chamberTypeId = Integer.parseInt(chamberTypeIdSet);
        boolean isChamberAvailable = chamberService.isChamberAvailable(chamberTypeId);
        if(isChamberAvailable) {
        //TODO
            String hospitalizationStatusStr = ParameterExtractor.extractString(Attribute.HOSPITALIZATION_STATUS, requestContext);
            HospitalizationStatus hospitalizationStatus = HospitalizationStatus.valueOf(hospitalizationStatusStr);

            Hospitalization hospitalization = Hospitalization.builder()
                    .patientId(patientCardId)
                    .status(hospitalizationStatus)
                    .build();
            //TODO
            ChamberStaying chamberStaying = ChamberStaying.builder()
                    .chamber_id(ParameterExtractor.extractInt(Attribute.CHAMBER_ID, requestContext))
                    .build();
        } else {

        }
        hospitalizationService.saveHospitalization();
        return null;
    }
}
