package com.epam.hospital.controller.command;

import com.epam.hospital.controller.request.RequestContext;

public interface Command {
    CommandResult execute(RequestContext requestContext);
}
