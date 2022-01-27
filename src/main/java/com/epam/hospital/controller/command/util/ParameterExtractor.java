package com.epam.hospital.controller.command.util;

import com.epam.hospital.controller.request.RequestContext;

import java.sql.Date;

public class ParameterExtractor {

    public static String extractString(String parameterName, RequestContext requestContext) {
        String paramValue = requestContext.getRequestParameter(parameterName);
        return paramValue;
    }
    public static int extractInt(String parameterName, RequestContext requestContext) {
        int paramValue = Integer.parseInt(requestContext.getRequestParameter(parameterName));
        return paramValue;
    }

    public static Date extractDate(String parameterName, RequestContext requestContext) {
        Date paramValue = Date.valueOf(requestContext.getRequestParameter(parameterName));
        return paramValue;
    }
}
