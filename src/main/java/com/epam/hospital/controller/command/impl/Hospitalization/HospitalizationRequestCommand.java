package com.epam.hospital.controller.command.impl.Hospitalization;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.controller.constant.CommandName;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.hospital.Chamber;
import com.epam.hospital.model.hospital.type.ChamberType;
import com.epam.hospital.model.treatment.ChamberStaying;
import com.epam.hospital.model.treatment.Hospitalization;
import com.epam.hospital.model.treatment.type.HospitalizationStatus;
import com.epam.hospital.service.database.ChamberService;

import com.epam.hospital.service.database.HospitalizationService;
import com.epam.hospital.service.database.impl.ChamberServiceImpl;
import com.epam.hospital.service.database.impl.HospitalizationServiceImpl;
import com.epam.hospital.service.exception.ServiceException;
import com.epam.hospital.util.constant.Attribute;
import com.epam.hospital.util.constant.Parameter;

public class HospitalizationRequestCommand implements Command {
    private static final String HOSPITALIZATION_REQUEST_PAGE_COMMAND = "MentalHospital?command=" + CommandName.HOSPITALIZATION_REQUEST_PAGE;
    private static final String CHAMBER_TYPE_UNAVAILABLE = "unavailable";
    private static final HospitalizationService hospitalizationService = HospitalizationServiceImpl.getInstance();
    private static final ChamberService chamberService = ChamberServiceImpl.getInstance();

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {

        String patentCardIdStr = requestContext.getSessionAttribute(Attribute.PATIENT_CARD_ID).toString();
        int patientCardId = Integer.parseInt(patentCardIdStr);

        String chamberTypeIdSet = ParameterExtractor.extractString(Parameter.CHAMBER_ID, requestContext);
        int chamberTypeId = Integer.parseInt(chamberTypeIdSet);
        ChamberType chamberType = chamberService.getChamberTypeById(chamberTypeId);

        if(chamberType.getNumberOfFreeRooms() >= 1) {

            Chamber chamber = chamberService.getAvailableChamber(chamberTypeId);
            int chamberId = chamber.getChamberId();
            if (chamber.getNumberOfFreeBeds() == chamberType.getNumberOfBeds()){
                int currentlyNumberOfFreeRooms = chamberType.getNumberOfFreeRooms();
                chamberType.setNumberOfFreeRooms(currentlyNumberOfFreeRooms-1);
                chamberService.updateChamberType(chamberType);
            }
            chamber.setNumberOfFreeBeds(chamber.getNumberOfFreeBeds()-1);
            chamberService.updateChamber(chamber);

            Hospitalization hospitalization = Hospitalization.builder()
                    .patientId(patientCardId)
                    .status(HospitalizationStatus.PENDING)
                    //TODO придет только имя доктора
                    .doctorId(ParameterExtractor.extractInt(Parameter.DOCTOR_ID,requestContext))
                    .build();

            ChamberStaying chamberStaying = ChamberStaying.builder()
                    .chamberId(chamberId)
                    .dateIn(ParameterExtractor.extractDate(Parameter.DATE_IN,requestContext))
                    .build();

            hospitalizationService.saveHospitalization(hospitalization, chamberStaying);
        } else {
            requestContext.addAttribute(Attribute.ERROR_MESSAGE, CHAMBER_TYPE_UNAVAILABLE);
        }
        return CommandResult.redirect(HOSPITALIZATION_REQUEST_PAGE_COMMAND);
    }
}
