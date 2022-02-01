package com.epam.hospital.controller.command.impl.Hospitalization;

import com.epam.hospital.constant.web.RequestAttributes;
import com.epam.hospital.constant.web.RequestParameters;
import com.epam.hospital.constant.web.SessionAttributes;
import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.constant.web.CommandName;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.hospital.Chamber;
import com.epam.hospital.model.hospital.type.ChamberType;
import com.epam.hospital.model.treatment.ChamberStaying;
import com.epam.hospital.model.treatment.Hospitalization;
import com.epam.hospital.model.treatment.type.HospitalizationStatus;
import com.epam.hospital.model.user.User;
import com.epam.hospital.service.database.ChamberService;

import com.epam.hospital.service.database.HospitalizationService;
import com.epam.hospital.service.database.UserService;
import com.epam.hospital.service.database.impl.ChamberServiceImpl;
import com.epam.hospital.service.database.impl.HospitalizationServiceImpl;
import com.epam.hospital.service.database.impl.UserServiceImpl;
import com.epam.hospital.service.exception.ServiceException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HospitalizationRequestCommand implements Command {
    private static final String HOSPITALIZATION_REQUEST_PAGE_COMMAND = "MentalHospital?command=" + CommandName.HOSPITALIZATION_REQUEST_PAGE;
    private static final String CHAMBER_TYPE_UNAVAILABLE = "unavailable";
    private static final HospitalizationService hospitalizationService = HospitalizationServiceImpl.getInstance();
    private static final ChamberService chamberService = ChamberServiceImpl.getInstance();
    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        int patientCardId = (Integer) requestContext.getSessionAttribute(SessionAttributes.PATIENT_CARD_ID);

        String chamberTypeIdString = ParameterExtractor.extractString(RequestParameters.CHAMBER_TYPE_ID, requestContext);
        int chamberTypeId = Integer.parseInt(chamberTypeIdString);
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

            String doctorName = ParameterExtractor.extractString(RequestParameters.DOCTOR, requestContext);
            List<String> fullName = new ArrayList<String>(Arrays.asList(doctorName.split(" ")));
            User doctor = userService.getUserByFullName(fullName.get(0), fullName.get(1));

            Hospitalization hospitalization = Hospitalization.builder()
                    .patientId(patientCardId)
                    .status(HospitalizationStatus.PENDING)
                    .doctorId(doctor.getUserId())
                    .build();

            ChamberStaying chamberStaying = ChamberStaying.builder()
                    .chamberId(chamberId)
                    .dateIn(new Date(System.currentTimeMillis()))
                    .build();

            hospitalizationService.saveHospitalization(hospitalization, chamberStaying);
        } else {
            requestContext.addAttribute(RequestAttributes.ERROR_MESSAGE, CHAMBER_TYPE_UNAVAILABLE);
        }
        return CommandResult.redirect(HOSPITALIZATION_REQUEST_PAGE_COMMAND);
    }
}
