package com.epam.hospital.controller.command.impl.admin;

import com.epam.hospital.constant.web.CommandName;
import com.epam.hospital.constant.web.RequestParameters;
import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.treatment.Disease;
import com.epam.hospital.model.treatment.Drug;
import com.epam.hospital.service.database.DiseaseService;
import com.epam.hospital.service.database.DrugService;
import com.epam.hospital.service.database.impl.DiseaseServiceImpl;
import com.epam.hospital.service.database.impl.DrugServiceImpl;
import com.epam.hospital.service.exception.ServiceException;

public class AddDrugCommand implements Command {
    private static final DrugService drugService = new DrugServiceImpl();
    private static final String ADD_DRUG_PAGE = "MentalHospital?command=" + CommandName.ADD_DRUG_PAGE;

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        Drug drug = Drug.builder()
                .name(ParameterExtractor.extractString(RequestParameters.NAME, requestContext))
                .build();
        drugService.save(drug);
        return CommandResult.redirect(ADD_DRUG_PAGE);
    }
}
