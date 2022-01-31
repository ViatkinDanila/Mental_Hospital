package com.epam.hospital.controller.command.impl.user;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.constant.CommandName;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.service.exception.ServiceException;
import com.epam.hospital.util.constant.Attribute;

public class SignOutCommand implements Command {
    private static final String HOME_PAGE_COMMAND = "controller?command=" + CommandName.HOME_PAGE;
    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        requestContext.addSession(Attribute.INVALID_ATTRIBUTE, true);

        return CommandResult.redirect(HOME_PAGE_COMMAND);
    }
}
