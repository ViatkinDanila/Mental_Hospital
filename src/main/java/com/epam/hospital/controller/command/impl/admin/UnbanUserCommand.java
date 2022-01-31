package com.epam.hospital.controller.command.impl.admin;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.controller.constant.CommandName;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.service.database.AdminService;
import com.epam.hospital.service.database.impl.AdminServiceImpl;
import com.epam.hospital.service.exception.ServiceException;
import com.epam.hospital.util.constant.Attribute;
import com.epam.hospital.util.constant.Parameter;

public class UnbanUserCommand implements Command {
    private static final AdminService adminService = AdminServiceImpl.getInstance();
    private static final String USER_ALREADY_BANNED = "user already banned";
    private static final String ALL_USERS_COMMAND = "MentalHospital?command=" + CommandName.USERS;
    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        int userId = ParameterExtractor.extractInt(Parameter.USER_ID, requestContext);
        if(!adminService.isUserBanned(userId)){
            adminService.setUserBanStatusById(userId, false);
        } else {
            requestContext.addAttribute(Attribute.ERROR_MESSAGE, USER_ALREADY_BANNED);
        }
        return CommandResult.redirect(ALL_USERS_COMMAND);
    }
}
