package com.epam.mentalhospital.controller.command;

import javax.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Command {
    CommandResult execute(HttpServletRequest req, HttpServletResponse resp);
}
