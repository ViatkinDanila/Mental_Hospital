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
        <form name="signupForm" method="POST" action="${pageContext.request.contextPath}/MentalHospital?command=sign-up"
              class="flex-box col-md-6">
            <h1 class="text-center text-primary"><fmt:message key="signup.reg"/></h1>
            <div class="mb-3">
                <span class="form-label"><fmt:message key="signup.email"/></span>
                <input type="email" name="login" minlength="5" maxlength="64" class="form-control"
                       required>
                <span class="email-error d-none text-danger"><fmt:message key="signup.error.email.isalready"/></span>
            </div>
            <div class="mb-3">
                <span class="form-label"><fmt:message key="signup.pass"/></span>
                <input type="password" name="password" id="currentPass" minlength="8" maxlength="32"
                       class="form-control password" required>
                <input type="checkbox" onclick="showPass()"> <fmt:message key="settings.current.password.show"/>
            </div>
            <div class="mb-3">
                <span class="form-label"><fmt:message key="signup.repeat.pass"/></span>
                <input type="password" class="form-control password" minlength="8" maxlength="32" required>
                <span class="password-error d-none text-danger"><fmt:message key="signup.repeat.pass.error"/></span>
            </div>
            <div class="mb-3">
                <span class="form-label"><fmt:message key="signup.first.name"/></span>
                <input type="text" name="firstName" minlength="4" maxlength="32" class="form-control" required>
            </div>
            <div class="mb-3">
                <span class="form-label"><fmt:message key="signup.last.name"/></span>
                <input type="text" name="lastName" minlength="4" maxlength="32" class="form-control" required>
            </div>

            <div class="mb-3">
                <span class="form-label"><fmt:message key="signup.phone.number"/></span>
                <input type="phone" pattern="[+]{1}[0-9]{12, 16}" name="phoneNumber" minlength="12" maxlength="18"
                       class="form-control">
            </div>
            <h3 class="text-danger error-message">
                <fmt:message key="login.error.${errorMessage}"/>
            </h3>
            <button type="submit" class="btn btn-primary w-25 d-flex mx-auto justify-content-center">
                <fmt:message key="signup.btn.submit"/>
            </button>
        </form>
    </div>
</div>
<script src="/js/sign-up.js"></script>
<script src="/js/checker-repeat-pas.js"></script>
</body>
</html>
