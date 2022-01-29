<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<html>

<html lang="<fmt:message key="html.lang"/>">
<head>
    <c:import url="/jsp/elements/head.jsp"/>
    <title><fmt:message key="label.title"/></title>
</head>
<body>

<body>
<c:import url="/jsp/elements/navbar.jsp"/>

<div class="container py-3">
    <div class="row flex-column align-items-center">
        <form name="signupForm" method="POST" action="${pageContext.request.contextPath}/MentalHospital?command=request-consultation"
              class="flex-box col-md-6">
            <h1 class="text-center text-primary"><fmt:message key="consultation.request"/></h1>
            <div class="mb-3">
                <span class="form-label"><fmt:message key="consultation.date"/></span>
                <input class="date"
                       id="date"
                       type="datetime-local"
                       max="2022-12-31T23:59"
                       placeholder="<fmt:message key="consultation.date.advice"/>"
                       name="date" value="${date}" required>
            </div>
            <div class="mb-3">
                <span class="form-label"><fmt:message key="consultation.duration.enter"/></span>
                <input name="duration" type="number" value="${duration}" min="10" max="180">
            </div>

            <select name="doctor" class="form-select form-select-lg mb-3" aria-label=".form-select-lg example">
                <option selected><fmt:message key="consultation.doctor.select"/></option>
                <option value="1">Alex Bros</option>
                <option value="2">Viatkin Daniiiiiiellaaa</option>
                <option value="3">Huan</option>
            </select>

            <div class="form-check form-switch mb-3">
                <input class="form-check-input" name="isOnline" type="checkbox" id="flexSwitchCheckDefault">
                <label class="form-check-label ml-1" for="flexSwitchCheckDefault"><fmt:message key="consultation.online"/></label>
            </div>

            <h3 class="text-danger error-message">
                <fmt:message key="consultation.request.error.${errorMessage}"/>
            </h3>
            <button type="submit" class="btn btn-primary w-25 d-flex mx-auto justify-content-center" style="font-size: 1.5rem">
                <fmt:message key="consultation.request.button"/>
            </button>
        </form>
    </div>
</div>
<script src="/js/set-min-date.js"></script>
</body>
</html>