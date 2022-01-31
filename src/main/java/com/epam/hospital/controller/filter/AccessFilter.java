package com.epam.hospital.controller.filter;

import com.epam.hospital.controller.constant.CommandName;
import com.epam.hospital.util.constant.Attribute;
import com.epam.hospital.util.constant.Parameter;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AccessFilter implements Filter{
    private static final String GUEST_ROLE = "GUEST";
    private static final String ADMIN_ROLE = "ADMIN";
    private static final String USER_ROLE = "USER";
    private static final String DOCTOR_ROLE = "DOCTOR";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        String commandName = servletRequest.getParameter(Parameter.COMMAND);
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession();
        String role = session.getAttribute(Attribute.ROLE).toString();

        if (commandName != null && role != null) {
            boolean isAccessAllowed = isAccessAllowed(commandName, role);
            if (!isAccessAllowed) {
                ((HttpServletResponse) servletRequest).sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        }
        doNextFilter(servletRequest, servletResponse, filterChain);
    }

    private void doNextFilter(ServletRequest servletRequest,
                              ServletResponse servletResponse,
                              FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isAccessAllowed(String commandName, String role) {
        if (commandName == null){
            return true;
        }
       switch (commandName){
           case CommandName.HOME_PAGE:
           case CommandName.SIGN_UP:
           case CommandName.SIGN_UP_PAGE:
           case CommandName.LOGIN:
           case CommandName.LOGIN_PAGE:
               return role.equalsIgnoreCase(GUEST_ROLE);
           case CommandName.CONSULTATION_PAGE:
           case CommandName.PROFILE_PAGE:
           case CommandName.CONSULTATION_REQUEST_PAGE:
           case CommandName.HOSPITALIZATION_REQUEST_PAGE:
               return role.equalsIgnoreCase(USER_ROLE);
           case CommandName.CONSULTATION_COMPLETE:
           case CommandName.CONSULTATION_APPROVE:
           case CommandName.CONSULTATION_REQUEST:
               return role.equalsIgnoreCase(DOCTOR_ROLE);
           case CommandName.BAN_USER:
           case CommandName.UNBAN_USER:
                return role.equalsIgnoreCase(ADMIN_ROLE);
       }
        return true;
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
