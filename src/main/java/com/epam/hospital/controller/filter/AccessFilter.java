package com.epam.hospital.controller.filter;

import com.epam.hospital.constant.web.CommandName;
import com.epam.hospital.constant.web.RequestAttributes;
import com.epam.hospital.constant.web.RequestParameters;
import com.epam.hospital.constant.web.SessionAttributes;
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

        String commandName = servletRequest.getParameter(RequestParameters.COMMAND);
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession();
        Object role = session.getAttribute(SessionAttributes.ROLE);

        if (commandName != null) {
            if (role == null) {
                role = "GUEST";
            }
            System.out.println(role);
            boolean isAccessAllowed = isAccessAllowed(commandName, role.toString());
            if (!isAccessAllowed) {
                ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_FORBIDDEN);
                servletRequest.setAttribute(RequestAttributes.ERROR_MESSAGE, "Permission denied");
                return;
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
       if (role.equals(GUEST_ROLE)) {
           return CommandName.SIGN_UP.equals(commandName) ||
                   CommandName.SIGN_UP_PAGE.equals(commandName) ||
                   CommandName.HOME_PAGE.equals(commandName) ||
                   CommandName.DISEASES.equals(commandName) ||
                   CommandName.DOCTORS.equals(commandName) ||
                   CommandName.LOGIN.equals(commandName) ||
                   CommandName.PROFILE_PAGE.equals(commandName) ||
                   CommandName.LOGIN_PAGE.equals(commandName);
       }

       if (role.equals(USER_ROLE)) {
           return CommandName.CONSULTATION_PAGE.equals(commandName) ||
                  CommandName.CONSULTATION_REQUEST_PAGE.equals(commandName) ||
                  CommandName.HOSPITALIZATION_REQUEST_PAGE.equals(commandName) ||
                   CommandName.HOSPITALIZATION_REQUEST.equalsIgnoreCase(commandName) ||
                   CommandName.HOME_PAGE.equals(commandName) ||
                   CommandName.DISEASES.equals(commandName) ||
                   CommandName.DOCTORS.equals(commandName) ||
                   CommandName.SIGN_OUT.equals(commandName) ||
                   CommandName.PROFILE_PAGE.equals(commandName) ||
                   CommandName.LOGIN_PAGE.equals(commandName);
       }

       if (role.equals(DOCTOR_ROLE)) {
           return CommandName.CONSULTATION_PAGE.equals(commandName) ||
                   CommandName.HOSPITALIZATION_REQUEST_PAGE.equals(commandName) ||
                   CommandName.DOCTORS.equals(commandName) ||
                   CommandName.DISEASES.equals(commandName) ||
                   CommandName.PROFILE_PAGE.equals(commandName) ||
                   CommandName.HOME_PAGE.equals(commandName) ||
                   CommandName.SIGN_OUT.equals(commandName) ||
                   CommandName.CONSULTATION_COMPLETE.equals(commandName) ||
                   CommandName.CONSULTATION_REJECT.equals(commandName) ||
                   CommandName.CONSULTATION_APPROVE.equals(commandName);
       }
//           case CommandName.SIGN_UP:
//           case CommandName.SIGN_UP_PAGE:
//           case CommandName.LOGIN_PAGE:
//               return role.equalsIgnoreCase(GUEST_ROLE);
//            CommandName.CONSULTATION_PAGE:
//            CommandName.CONSULTATION_REQUEST_PAGE:
//            CommandName.HOSPITALIZATION_REQUEST_PAGE:
//            CommandName.PROFILE_PAGE:
//               return role.equalsIgnoreCase(USER_ROLE);
//           case CommandName.CONSULTATION_COMPLETE:
//           case CommandName.CONSULTATION_APPROVE:
//           case CommandName.CONSULTATION_REQUEST:
//               return role.equalsIgnoreCase(DOCTOR_ROLE);
//           case CommandName.BAN_USER:
//           case CommandName.UNBAN_USER:
//                return role.equalsIgnoreCase(ADMIN_ROLE);
        return true;
    }

    @Override
    public void destroy() {

    }

}
