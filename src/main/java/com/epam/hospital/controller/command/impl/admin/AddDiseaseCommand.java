package com.epam.hospital.controller.command.impl.admin;

import com.epam.hospital.constant.web.CommandName;
import com.epam.hospital.constant.web.RequestParameters;
import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.treatment.Disease;
import com.epam.hospital.service.database.DiseaseService;
import com.epam.hospital.service.database.impl.DiseaseServiceImpl;
import com.epam.hospital.service.exception.ServiceException;

public class AddDiseaseCommand implements Command {
    private static final DiseaseService diseaseService = new DiseaseServiceImpl();
    private static final String ADD_DISEASE_PAGE = "MentalHospital?command=" + CommandName.ADD_DISEASE_PAGE;

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        Disease disease = Disease.builder()
                .name(ParameterExtractor.extractString(RequestParameters.NAME, requestContext))
                .description(ParameterExtractor.extractString(RequestParameters.DESCRIPTION, requestContext))
                .build();
        diseaseService.save(disease);
        return CommandResult.redirect(ADD_DISEASE_PAGE);
    }
}
