package com.epam.hospital.controller.command.impl.user;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.constant.Page;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.dto.DoctorDto;
import com.epam.hospital.model.treatment.Disease;
import com.epam.hospital.model.user.User;
import com.epam.hospital.model.user.info.DoctorInfo;
import com.epam.hospital.service.database.DiseaseService;
import com.epam.hospital.service.database.UserService;
import com.epam.hospital.service.database.impl.DiseaseServiceImpl;
import com.epam.hospital.service.database.impl.UserServiceImpl;
import com.epam.hospital.service.exception.ServiceException;
import com.epam.hospital.util.constant.Attribute;

import java.util.ArrayList;
import java.util.List;

public class GetAllDoctorsCommand implements Command {
    private final static UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        List<User> doctorsList = userService.getAllDoctors(3);
        List<DoctorInfo> doctorsInfoList = new ArrayList<>();
        for(User doctor : doctorsList){
            int doctorId = doctor.getUserId();
            doctorsInfoList.add(userService.getDoctorInfoById(doctorId));
        }
        List<DoctorDto> doctorDtos = new ArrayList<>();
        for(int i = 0; i < doctorsInfoList.size() && i < doctorsList.size(); i++){
            DoctorDto doctorDto = DoctorDto.builder()
                    .workExperience(doctorsInfoList.get(i).getWorkExperience())
                    .specialization(doctorsInfoList.get(i).getSpecialization())
                    .classification(doctorsInfoList.get(i).getClassification())
                    .userRole("DOCTOR")
                    .number(doctorsList.get(i).getNumber())
                    .firstname(doctorsList.get(i).getFirstName())
                    .lastname(doctorsList.get(i).getLastName())
                    .number(doctorsList.get(i).getNumber())
                    .build();
            doctorDtos.add(doctorDto);
        }

        requestContext.addAttribute(Attribute.ALL_DOCTORS, doctorDtos);
        return CommandResult.forward(Page.DOCTORS);
    }
}
