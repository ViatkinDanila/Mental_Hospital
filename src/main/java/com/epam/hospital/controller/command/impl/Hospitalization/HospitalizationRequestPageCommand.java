package com.epam.hospital.controller.command.impl.Hospitalization;

import com.epam.hospital.constant.web.Page;
import com.epam.hospital.constant.web.RequestAttributes;
import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.hospital.type.ChamberType;
import com.epam.hospital.model.user.User;
import com.epam.hospital.service.database.ChamberService;
import com.epam.hospital.service.database.UserService;
import com.epam.hospital.service.database.impl.ChamberServiceImpl;
import com.epam.hospital.service.database.impl.UserServiceImpl;
import com.epam.hospital.service.exception.ServiceException;

import java.util.List;

public class HospitalizationRequestPageCommand implements Command {
    private final ChamberService chamberService = ChamberServiceImpl.getInstance();
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        List<User> doctors = userService.getAllDoctors(3);
        requestContext.addAttribute(RequestAttributes.DOCTORS, doctors);

        List<ChamberType> chamberTypeList = chamberService.getAllChamberTypes();
        requestContext.addAttribute(RequestAttributes.CHAMBER_TYPES, chamberTypeList);

        return CommandResult.forward(Page.REQUEST_HOSPITALIZATION_PAGE);
    }
}
