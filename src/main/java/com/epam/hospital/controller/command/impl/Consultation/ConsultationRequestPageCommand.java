package com.epam.hospital.controller.command.impl.Consultation;

import com.epam.hospital.constant.web.RequestAttributes;
import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.constant.web.Page;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.user.User;
import com.epam.hospital.service.database.UserService;
import com.epam.hospital.service.database.impl.UserServiceImpl;
import com.epam.hospital.service.exception.ServiceException;

import java.util.List;

public class ConsultationRequestPageCommand implements Command {
    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        List<User> doctors = userService.getAllDoctors(3);
        requestContext.addAttribute(RequestAttributes.DOCTORS, doctors);

        return CommandResult.forward(Page.REQUEST_CONSULTATION_PAGE);
    }
}
