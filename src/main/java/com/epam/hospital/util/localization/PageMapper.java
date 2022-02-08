package com.epam.hospital.util.localization;

import com.epam.hospital.constant.web.CommandName;
import com.epam.hospital.constant.web.RequestAttributes;
import com.epam.hospital.constant.web.RequestParameters;
import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.controller.request.RequestContext;

public class PageMapper {
    private static final String PAGE = "MentalHospital?command=";
    private static final String LOGIN_PAGE = "MentalHospital?command=";
    private static final String PARAMETER_SPLITERATOR = "&";
    //TODO брать id  из хедера
    public String takePage(RequestContext requestContext){
        String url = requestContext.getHeader();
        String action =  extractCommand(url);

        if (action.equals(CommandName.CONSULTATION_APPROVE) ||
                action.equals(CommandName.CONSULTATION_COMPLETE) ||
                action.equals(CommandName.CONSULTATION_REQUEST) ||
                action.equals(CommandName.CONSULTATION_REJECT)){
            return PAGE + CommandName.CONSULTATION_PAGE + PARAMETER_SPLITERATOR +
                    "id=" + ParameterExtractor.extractInt(RequestParameters.ID, requestContext);
        }
        if (action.equals(CommandName.HOSPITALIZATION_COMPLETE) ||
            action.equals(CommandName.HOSPITALIZATION_REQUEST) ||
            action.equals(CommandName.HOSPITALIZATION_REJECT) ||
            action.equals(CommandName.HOSPITALIZATION_APPROVE)){
            return PAGE + CommandName.HOSPITALIZATION_PAGE + PARAMETER_SPLITERATOR +
                    "id=" + ParameterExtractor.extractInt(RequestParameters.ID, requestContext);
        }
        if (action.equals(CommandName.SIGN_UP) ||
            action.equals(CommandName.LOGIN) ||
            action.equals(CommandName.SIGN_OUT)){
            return PAGE + CommandName.HOME_PAGE;
        }
        if (action.contains(CommandName.BAN) ||
            action.contains(CommandName.UNBAN)){
            return PAGE + CommandName.PROFILE_PAGE + PARAMETER_SPLITERATOR +
                    "id=" + ParameterExtractor.extractInt(RequestParameters.ID, requestContext);
        }
        return url;
    }

    private String extractCommand(String url) {
        int commandIndex = url.indexOf(RequestParameters.COMMAND) + RequestParameters.COMMAND.length() + 1;
        int lastCommandIndex = url.indexOf(PARAMETER_SPLITERATOR);
        if (lastCommandIndex == -1) {
            return url.substring(commandIndex);
        } else {
            return url.substring(commandIndex, lastCommandIndex);
        }
    }

    private String getIdFromHeader(String url){
        int indexOfId = url.indexOf("id=");
        int lastIndexOfId = 0;
        return null;
    }
}
