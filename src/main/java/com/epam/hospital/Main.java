package com.epam.hospital;

import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.dto.ConsultationDto;
import com.epam.hospital.model.dto.UserInfoDto;
import com.epam.hospital.util.date.DateFormatter;
import com.epam.hospital.util.date.DateFormatterType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Map<String, Object> requestAttributes = new HashMap<>();
        Map<String, Object> sessionAttributes = new HashMap<>();;
        Map<String, String[]> requestParameters = new HashMap<>();;
        String[] a = {"2022-02-12T14:14",""};
        requestParameters.put("me",a);
        RequestContext requestContext = new RequestContext(requestAttributes,requestParameters,sessionAttributes,"me");
        System.out.println(ParameterExtractor.extractDate("me",requestContext));

    }
}
