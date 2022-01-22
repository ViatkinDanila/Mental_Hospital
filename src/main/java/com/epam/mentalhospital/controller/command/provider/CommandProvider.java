package com.epam.mentalhospital.controller.command.provider;

import com.epam.mentalhospital.controller.command.Command;
import com.epam.mentalhospital.controller.command.CommandName;
import com.epam.mentalhospital.controller.command.impl.GoToConsultationPageCommand;
import com.epam.mentalhospital.controller.command.impl.SignInCommand;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.SIGN_IN, new SignInCommand());
        commands.put(CommandName.GOTO_CONSULTATION_PAGE, new GoToConsultationPageCommand());
    }

    public final Command getCommand(String commandName) {
        Command command = commands.get(commandName);
        return command;
    }
}
