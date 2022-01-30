package com.epam.hospital.controller.command.impl;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.dto.DoctorDto;
import com.epam.hospital.model.user.User;
import com.epam.hospital.model.user.info.DoctorInfo;
import com.epam.hospital.service.database.ConsultationService;
import com.epam.hospital.service.database.UserService;
import com.epam.hospital.service.database.impl.ConsultationServiceImpl;
import com.epam.hospital.service.database.impl.UserServiceImpl;
import com.epam.hospital.service.exception.ServiceException;
import com.epam.hospital.util.constant.Attribute;
import com.epam.hospital.util.constant.Parameter;

public class DoctorProfileCommand implements Command {
    private static final UserService userService = UserServiceImpl.getInstance();
    private static final String DOCTOR_ROLE = "DOCTOR";

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {

        String doctorIdStr = requestContext.getSessionAttribute(Attribute.USER_ID).toString();
        int doctorId = Integer.parseInt(doctorIdStr);

        DoctorInfo doctorInfo = userService.getDoctorInfoById(doctorId);
        User doctor = userService.getUserById(doctorId);

        DoctorDto doctorDto = DoctorDto.builder()
                .lastname(doctor.getLastname())
                .firstname(doctor.getFirstname())
                .email(doctor.getEmail())
                .number(doctor.getNumber())
                .userRole(DOCTOR_ROLE)
                .classification(doctorInfo.getClassification())
                .specialization(doctorInfo.getSpecialization())
                .workExperience(doctorInfo.getWorkExperience())
                .build();
        requestContext.addAttribute("doctorInfo", doctorDto);

        return null;
    }
}
