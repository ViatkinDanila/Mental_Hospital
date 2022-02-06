package com.epam.hospital.controller.command.provider;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.impl.Consultation.*;
import com.epam.hospital.controller.command.impl.ForwardPageCommand;
import com.epam.hospital.controller.command.impl.Hospitalization.*;
import com.epam.hospital.controller.command.impl.HospitalizationPageCommand;
import com.epam.hospital.controller.command.impl.LocalizationCommand;
import com.epam.hospital.controller.command.impl.admin.BanUserCommand;
import com.epam.hospital.controller.command.impl.admin.UnbanUserCommand;
import com.epam.hospital.controller.command.impl.disease.GetAllDiseasesCommand;
import com.epam.hospital.controller.command.impl.user.*;
import com.epam.hospital.constant.web.CommandName;
import com.epam.hospital.controller.command.impl.ConsultationPageCommand;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.CONSULTATION_PAGE, new ConsultationPageCommand());
        commands.put(CommandName.HOSPITALIZATION_PAGE, new HospitalizationPageCommand());
        commands.put(CommandName.SIGN_UP, new SignUpCommand());
        commands.put(CommandName.SIGN_OUT, new SignOutCommand());
        commands.put(CommandName.LOGIN, new LoginCommand());
        commands.put(CommandName.CONSULTATION_COMPLETE, new ConsultationCompleteCommand());
        commands.put(CommandName.CONSULTATION_APPROVE, new ConsultationApproveCommand());
        commands.put(CommandName.CONSULTATION_REQUEST, new ConsultationRequestCommand());
        commands.put(CommandName.CONSULTATION_REJECT, new ConsultationRejectCommand());
        commands.put(CommandName.HOSPITALIZATION_REQUEST, new HospitalizationRequestCommand());
        commands.put(CommandName.HOSPITALIZATION_COMPLETE, new HospitalizationCompleteCommand());
        commands.put(CommandName.HOSPITALIZATION_REJECT, new HospitalizationRejectCommand());
        commands.put(CommandName.HOSPITALIZATION_APPROVE, new HospitalizationApproveCommand());
        commands.put(CommandName.BAN, new BanUserCommand());
        commands.put(CommandName.UNBAN, new UnbanUserCommand());
        commands.put(CommandName.LOCALIZATION, new LocalizationCommand());

        commands.put(CommandName.HOME_PAGE, new ForwardPageCommand());
        commands.put(CommandName.SIGN_UP_PAGE, new ForwardPageCommand());
        commands.put(CommandName.LOGIN_PAGE, new ForwardPageCommand());
        commands.put(CommandName.DOCTORS, new GetAllDoctorsCommand());
        commands.put(CommandName.DISEASES, new GetAllDiseasesCommand());
        commands.put(CommandName.USERS, new GetAllUsersCommand());
        commands.put(CommandName.PROFILE_PAGE, new ProfilePageCommand());
        commands.put(CommandName.CONSULTATION_REQUEST_PAGE, new ConsultationRequestPageCommand());
        commands.put(CommandName.HOSPITALIZATION_REQUEST_PAGE, new HospitalizationRequestPageCommand());

    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
