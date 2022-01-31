package com.epam.hospital.controller.command.impl.Hospitalization;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.controller.constant.CommandName;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.treatment.Hospitalization;
import com.epam.hospital.model.treatment.type.HospitalizationStatus;
import com.epam.hospital.service.database.HospitalizationService;
import com.epam.hospital.service.database.impl.HospitalizationServiceImpl;
import com.epam.hospital.service.exception.ServiceException;
import com.epam.hospital.util.constant.Attribute;
import com.epam.hospital.util.constant.Parameter;

public class HospitalizationApproveCommand implements Command {
    private static final String PROFILE_PAGE_COMMAND = "MentalHospital?command=" + CommandName.PROFILE_PAGE;

    HospitalizationService hospitalizationService = new HospitalizationServiceImpl();
    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {

        int doctorId = Integer.parseInt(requestContext.getSessionAttribute(Attribute.DOCTOR_ID).toString());

        int hospitalizationId = ParameterExtractor.extractInt(Parameter.HOSPITALIZATION_ID, requestContext);
        Hospitalization hospitalization = hospitalizationService.getHospitalizationById(hospitalizationId);

        hospitalization.setDoctorId(doctorId);
        hospitalization.setStatus(HospitalizationStatus.ACTIVE);
        hospitalizationService.update(hospitalization);
        return CommandResult.redirect(PROFILE_PAGE_COMMAND);
    }
}
