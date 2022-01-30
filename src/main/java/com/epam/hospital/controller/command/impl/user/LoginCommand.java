package com.epam.hospital.controller.command.impl.user;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.controller.constant.CommandName;
import com.epam.hospital.controller.constant.Page;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.dao.PatientCardDao;
import com.epam.hospital.model.user.User;
import com.epam.hospital.service.database.PatientCardService;
import com.epam.hospital.service.database.TreatmentCourseService;
import com.epam.hospital.service.database.UserService;
import com.epam.hospital.service.database.impl.PatientCardServiceImpl;
import com.epam.hospital.service.database.impl.TreatmentCourseServiceImpl;
import com.epam.hospital.service.database.impl.UserServiceImpl;
import com.epam.hospital.service.exception.ServiceException;
import com.epam.hospital.util.Hasher;
import com.epam.hospital.util.constant.Attribute;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class LoginCommand implements Command {
    private static final byte[] salt = UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8);
    private static final String INCORRECT_DATA_KEY = "incorrect";
    private static final String BANNED_USER_KEY = "banned";
    private static final String USER_PATIENT_ROLE = "PATIENT";
    private static final String HOME_PAGE_COMMAND = "controller?command=" + CommandName.HOME_PAGE;


    private static final UserService userService = UserServiceImpl.getInstance();
    private static final PatientCardService patientCardService = PatientCardServiceImpl.getInstance();

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {

        String email = ParameterExtractor.extractString(Attribute.LOGIN, requestContext);
        String password = ParameterExtractor.extractString(Attribute.PASSWORD, requestContext);

        Hasher hasher = new Hasher();
        String hashPassword = hasher.hashString(password, salt);

        User user = userService.login(email, hashPassword);
        if (user != null){
            if (user.isBanned()) {
                String role = userService.getUserRoleById(user.getUserRoleId());
                int patientCardId = patientCardService.getPatientCardIdByUserId(user.getUserId());
                requestContext.addSession(Attribute.USER_ID, user.getUserId());
                requestContext.addSession(Attribute.ROLE, role);
                if (role.equals(USER_PATIENT_ROLE)){
                    requestContext.addSession(Attribute.PATIENT_CARD_ID, patientCardId);
                }
                return CommandResult.redirect(HOME_PAGE_COMMAND);
            } else {
                requestContext.addAttribute(Attribute.ERROR_MESSAGE, BANNED_USER_KEY);
            }
        } else {
            requestContext.addAttribute(Attribute.ERROR_MESSAGE, INCORRECT_DATA_KEY);
        }
        return CommandResult.redirect(Page.SIGN_IN_PAGE);
    }
}
