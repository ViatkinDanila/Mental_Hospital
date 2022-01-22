package com.epam.mentalhospital.controller.command.impl;

import com.epam.mentalhospital.controller.command.Command;
import com.epam.mentalhospital.controller.command.CommandResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignInCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        return null;
    }
}
