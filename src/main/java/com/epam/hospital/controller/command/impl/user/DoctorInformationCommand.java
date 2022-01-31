package com.epam.hospital.controller.command.impl.user;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.controller.constant.Page;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.dto.DoctorDto;
import com.epam.hospital.model.user.User;
import com.epam.hospital.model.user.info.DoctorInfo;
import com.epam.hospital.service.database.UserService;
import com.epam.hospital.service.database.impl.UserServiceImpl;
import com.epam.hospital.service.exception.ServiceException;
import com.epam.hospital.util.constant.Attribute;
import com.epam.hospital.util.constant.Parameter;

public class DoctorInformationCommand implements Command {
    private final static UserService userService = UserServiceImpl.getInstance();
    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        int doctorId = ParameterExtractor.extractInt(Parameter.DOCTOR_ID, requestContext);
        User doctor = userService.getUserById(doctorId);
        DoctorInfo doctorInfo = userService.getDoctorInfoById(doctorId);
        DoctorDto doctorDto = DoctorDto.builder()
                .workExperience(doctorInfo.getWorkExperience())
                .specialization(doctorInfo.getSpecialization())
                .classification(doctorInfo.getClassification())
                .userRole("DOCTOR")
                .number(doctor.getNumber())
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .number(doctor.getNumber())
                .build();
        requestContext.addAttribute(Attribute.DOCTOR, doctor);
        return CommandResult.forward(Page.DOCTOR);
    }
}
