package com.epam.hospital.util.tag;

import com.epam.hospital.util.constant.Attribute;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.tagext.TagSupport;

public class AccessTag extends TagSupport {
    private static final String GUEST = "GUEST";
    private static final String NOT_GUEST = "NOT_GUEST";

    private String role;

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int doStartTag() {
        HttpSession session =   pageContext.getSession();
        Object role = session.getAttribute(Attribute.ROLE);


        if (role == null) {
            if (GUEST.equalsIgnoreCase(this.role)) {
                return EVAL_BODY_INCLUDE;
            }
        } else if (role.toString().equalsIgnoreCase(this.role)
                || NOT_GUEST.equalsIgnoreCase(this.role)) {
            return EVAL_BODY_INCLUDE;
        }
        return SKIP_BODY;
    }
}
