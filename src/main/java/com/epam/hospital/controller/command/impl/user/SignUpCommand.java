package com.epam.hospital.controller.command.impl.user;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.controller.constant.CommandName;
import com.epam.hospital.controller.constant.Page;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.treatment.PatientCard;
import com.epam.hospital.model.user.User;
import com.epam.hospital.service.database.SignUpService;
import com.epam.hospital.service.database.UserService;
import com.epam.hospital.service.database.impl.SignUpServiceImpl;
import com.epam.hospital.service.database.impl.UserServiceImpl;
import com.epam.hospital.service.exception.ServiceException;
import com.epam.hospital.util.Hasher;
import com.epam.hospital.util.constant.Attribute;
import com.epam.hospital.util.constant.Parameter;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class SignUpCommand implements Command {
    private static final byte[] salt = UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8);
    private static final String LOGIN_PAGE_COMMAND = "controller?command=" + CommandName.SIGN_IN_PAGE;
    private static final UserService userService = UserServiceImpl.getInstance();
    private static final String INVALID_LOGIN_KEY = "invalid.login";
    private static final int USER_ROLE_ID = 1;
    private final SignUpService sigUpService = SignUpServiceImpl.getInstance();

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String login = ParameterExtractor.extractString(Attribute.LOGIN, requestContext);
        boolean isUserExist = userService.isUserExist(login);
        if (!isUserExist){
            requestContext.addAttribute(Attribute.LOGIN, login);

            String password = requestContext.getRequestParameter(Attribute.PASSWORD);
            Hasher hasher = new Hasher();
            String hashedPassword = hasher.hashString(password, salt);
            User user = User.builder()
                    .email(login)
                    .hashedPassword(hashedPassword)
                    .firstname(ParameterExtractor.extractString(Parameter.FIRST_NAME, requestContext))
                    .isBanned(false)
                    .lastname(ParameterExtractor.extractString(Parameter.LAST_NAME, requestContext))
                    .userRoleId(USER_ROLE_ID)
                    .number(ParameterExtractor.extractString(Parameter.PHONE_NUMBER, requestContext))
                    .build();
            PatientCard patientCard = PatientCard.builder()
                    .age(ParameterExtractor.takeInt(Parameter.AGE, requestContext))
                    .sex(ParameterExtractor.extractString(Parameter.SEX, requestContext))
                    .spareNumber(ParameterExtractor.extractString(Parameter.SPARE_PHONE_NUMBER, requestContext))
                    .build();
            sigUpService.signUp(user, patientCard);
            return CommandResult.redirect(LOGIN_PAGE_COMMAND);
        } else {
            requestContext.addAttribute(Attribute.ERROR_MESSAGE, INVALID_LOGIN_KEY);
        }
        return CommandResult.forward(Page.SIGN_UP_PAGE);
    }
}
