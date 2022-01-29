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

<div class="container d-flex flex-column h3">
    <span class="h1 text-dark m-auto p-5">HOME PAGE</span>
    <a class="h3 m-auto p-5" href="${pageContext.request.contextPath}/MentalHospital?command=consultation&id=1">Get consultation by id=1</a>
    <a class="h3 m-auto p-5" href="${pageContext.request.contextPath}/MentalHospital?command=hospitalization&id=1">Get hospitalization by id=1</a>

    <a class="h3 m-auto p-5" href="${pageContext.request.contextPath}/MentalHospital?command=user-profile&elements=consultation">Get all consultations (profile page)</a>
    <a class="h3 m-auto p-5" href="${pageContext.request.contextPath}/MentalHospital?command=hospitalizations&elements=hospitalization">Get all hospitalizations (profile page)</a>

    <a class="h3 m-auto p-5" href="${pageContext.request.contextPath}/MentalHospital?command=doctor&id=1">Get doctor by id=1</a>
    <a class="h3 m-auto p-5" href="${pageContext.request.contextPath}/MentalHospital?command=doctors">Get all doctors</a>

    <a class="h3 m-auto p-5" href="${pageContext.request.contextPath}/MentalHospital?command=user&id=1">Get user by id=1</a>
</div>

</body>
</html>