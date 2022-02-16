package com.epam.hospital.controller.command.impl.disease;

import com.epam.hospital.constant.web.RequestAttributes;
import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.constant.web.Page;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.treatment.Disease;
import com.epam.hospital.service.logic.DiseaseService;
import com.epam.hospital.service.logic.impl.DiseaseServiceImpl;
import com.epam.hospital.service.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class GetAllDiseasesCommand implements Command {
    private final static DiseaseService diseaseService = DiseaseServiceImpl.getInstance();

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        List<Disease> diseaseList = diseaseService.getAll();
        log.info("diseases: {}", diseaseList);
        requestContext.addAttribute(RequestAttributes.DISEASES, diseaseList);
        return CommandResult.forward(Page.DISEASES);
    }
}
