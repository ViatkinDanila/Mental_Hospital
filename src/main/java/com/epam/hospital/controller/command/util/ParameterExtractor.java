package com.epam.hospital.controller.command.util;

import com.epam.hospital.controller.request.RequestContext;

public class ParameterExtractor {

    public static String extractString(String parameterName, RequestContext requestContext) {
        String paramValue = requestContext.getRequestParameter(parameterName);
        return paramValue;
    }

    public static int takeInt(String parameterName, RequestContext requestContext) {
        int paramValue = Integer.parseInt(requestContext.getRequestParameter(parameterName));
        return paramValue;
    }
}
