package com.epam.hospital.controller.command.impl.disease;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.constant.Page;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.treatment.Disease;
import com.epam.hospital.service.database.DiseaseService;
import com.epam.hospital.service.database.impl.DiseaseServiceImpl;
import com.epam.hospital.service.exception.ServiceException;
import com.epam.hospital.util.constant.Attribute;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class GetAllDiseasesCommand implements Command {
    private final static DiseaseService diseaseService = DiseaseServiceImpl.getInstance();

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        List<Disease> diseaseList = diseaseService.getAll();
        log.info("diseases: {}", diseaseList);
        requestContext.addAttribute(Attribute.DISEASES, diseaseList);
        return CommandResult.forward(Page.DISEASES);
    }
}
