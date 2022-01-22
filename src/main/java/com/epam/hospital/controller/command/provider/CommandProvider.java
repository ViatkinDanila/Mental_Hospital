package com.epam.hospital.controller.command.provider;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.impl.ForwardPageCommand;
import com.epam.hospital.controller.constant.CommandName;
import com.epam.hospital.controller.command.impl.ConsultationPageCommand;
import com.epam.hospital.controller.command.impl.SignInCommand;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.SIGN_IN, new SignInCommand());

        commands.put(CommandName.GOTO_CONSULTATION_PAGE, new ConsultationPageCommand());
        commands.put(CommandName.HOME_PAGE, new ForwardPageCommand());
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
