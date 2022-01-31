package com.epam.hospital.controller.command.impl.disease;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.controller.constant.Page;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.treatment.Disease;
import com.epam.hospital.service.database.DiseaseService;
import com.epam.hospital.service.database.impl.DiseaseServiceImpl;
import com.epam.hospital.service.exception.ServiceException;
import com.epam.hospital.util.constant.Attribute;
import com.epam.hospital.util.constant.Parameter;

import java.util.List;

public class GetDiseaseCommand implements Command {
    private final static DiseaseService diseaseService = DiseaseServiceImpl.getInstance();
    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String diseaseIdStr = ParameterExtractor.extractString(Parameter.DISEASE_ID, requestContext);
        int diseasesId = Integer.parseInt(diseaseIdStr);
        Disease diseaseList = diseaseService.getDiseaseById(diseasesId);
        requestContext.addAttribute(Attribute.ALL_DISEASES, diseaseList);
        return CommandResult.forward(Page.DISEASES);
    }
}
