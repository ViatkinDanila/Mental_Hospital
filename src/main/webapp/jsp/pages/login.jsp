<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<html lang="<fmt:message key="html.lang"/>">
<head>
    <c:import url="/jsp/elements/head.jsp"/>
    <title><fmt:message key="label.title"/></title>
</head>
<body>

<c:import url="/jsp/elements/navbar.jsp"/>

<div class="container py-3">
    <div class="row flex-column align-items-center">
        <form method="POST" name="loginForm"  action="${pageContext.request.contextPath}/MentalHospital?command=login" class="flex-box col-md-6">
            <h1 class="text-center text-primary"><fmt:message key="login.log.in"/></h1>
            <div class="mb-3">
                <span class="form-label"><fmt:message key="login.login"/></span>
                <input type="email" name="login" minlength="5" maxlength="64" class="form-control" value="${login}" required>
            </div>
            <div class="mb-3">
                <span class="form-label"><fmt:message key="login.password"/></span>
                <input type="password" id="currentPass" name="password" class="form-control" minlength="8" maxlength="32" required>
                <input type="checkbox" onclick="showPass()"> <fmt:message key="settings.current.password.show"/>
                <script>
                    const currentPass = document.getElementById('currentPass');
                    function showPass() {
                        if (currentPass.type === "password") {
                            currentPass.type = "text";
                        } else {
                            currentPass.type = "password";
                        }
                    }
                </script>
            </div>
            <h3 class="text-danger error-message">
                <fmt:message key="login.error.${errorMessage}"/>
            </h3>
            <br/>
            <button type="submit" name="Log in" class="btn btn-primary w-25 d-flex mx-auto justify-content-center">
                <fmt:message key="login.submit"/>
            </button>
        </form>
    </div>
</div>
</body>
</html>
