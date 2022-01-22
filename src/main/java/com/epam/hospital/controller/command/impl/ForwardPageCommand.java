package com.epam.hospital.controller.command.impl;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.constant.CommandName;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.constant.Page;
import com.epam.hospital.controller.constant.RequestParameter;
import com.epam.hospital.controller.request.RequestContext;

import java.util.HashMap;
import java.util.Map;

public class ForwardPageCommand implements Command {
    private final Map<String, String> commandPages = new HashMap<>();

    public ForwardPageCommand() {
        commandPages.put(CommandName.HOME_PAGE, Page.HOME_PAGE);
    }

    @Override
    public CommandResult execute(RequestContext requestContext) {
        String command = requestContext.getRequestParameter(RequestParameter.COMMAND);
        String page = commandPages.get(command);
        return CommandResult.forward(page);
    }
}
