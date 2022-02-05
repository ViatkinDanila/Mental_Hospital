package com.epam.hospital.controller.command.impl;

import com.epam.hospital.constant.web.RequestAttributes;
import com.epam.hospital.constant.web.RequestParameters;
import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.service.exception.ServiceException;

public class LocalizationCommand implements Command {
    private static final String RU = "ru";
    private static final String EN_LOCALE = "en_US";
    private static final String RU_LOCALE = "ru_RU";

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String language = ParameterExtractor.extractString(RequestParameters.LANGUAGE, requestContext);
        String locale = getLocaleByLanguage(language);
        requestContext.addSession(RequestAttributes.LANGUAGE, locale);

        return null;
    }

    private String getLocaleByLanguage(String language) {
         if (RU.equals(language)){
             return RU_LOCALE;
         }
         return EN_LOCALE;
    }

}
