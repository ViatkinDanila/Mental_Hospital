package com.epam.hospital.controller.command.impl.user;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.constant.Page;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.dto.DoctorDto;
import com.epam.hospital.model.dto.UserInfoDto;
import com.epam.hospital.model.user.User;
import com.epam.hospital.model.user.info.DoctorInfo;
import com.epam.hospital.service.database.UserService;
import com.epam.hospital.service.database.impl.UserServiceImpl;
import com.epam.hospital.service.exception.ServiceException;
import com.epam.hospital.util.constant.Attribute;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GetAllUsersCommand implements Command {
    private final static UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        List<User> users = userService.getAll();
        List<UserInfoDto> userInfoDtos = new ArrayList<>();
        for (User user : users) {
            UserInfoDto userInfoDto = UserInfoDto.builder()
                    .email(user.getEmail())
                    .fullName(user.getFirstName() + " " + user.getLastName())
                    .status(user.isBanned() ? "BANNED" : "ACTIVE")
                    .role(user.getUserRoleId() == 1 ? "USER" : "ADMIN")
                    .id(user.getUserId())
                    .build();
            userInfoDtos.add(userInfoDto);
        }
        log.info("users: {}", users);

        requestContext.addAttribute(Attribute.USERS, userInfoDtos);
        return CommandResult.forward(Page.USERS);
    }
}
