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
        Object role = session.getAttribute(Attribute.ROLE);

        if (commandName != null && role != null) {
            boolean isAccessAllowed = isAccessAllowed(commandName, role.toString());
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
       }
        return true;
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
