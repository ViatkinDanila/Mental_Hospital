<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<html>

<head>
    <c:import url="/jsp/elements/head.jsp"/>
    <title>Mental Hospital</title>
</head>
<body>
<c:import url="/jsp/elements/navbar.jsp"/>

<div class="container-fluid">
    <div class="container bg-secondary d-flex flex-column p-3 justify-content-center h5">
        <div class="d-flex justify-content-center p-1">
            <span>Consultation id:</span>
            <span class="ml-2">${consultation.getConsultationId()}</span>
        </div>
        <div class="d-flex justify-content-center p-1">
            <span>Date:</span>
            <span class="ml-2">${consultation.getDate()}</span>
        </div>
        <div class="d-flex justify-content-center p-1">
            <span>Duration:</span>
            <span class="ml-2">${consultation.getDuration()}</span>
        </div>
        <div class="d-flex justify-content-center p-1">
            <span>Communication type:</span>
            <span class="ml-2">${consultation.getCommunicationType()}</span>
        </div>
        <div class="d-flex justify-content-center p-1">
            <span>Doctor:</span>
            <span class="ml-2">${consultation.getDoctorFirstName()}</span>
            <span class="ml-1">${consultation.getDoctorLastName()}</span>
        </div>
        <div class="d-flex justify-content-center p-1">
            <span>Diseases:</span>
            <span class="ml-2">${consultation.getDiseases()}</span>
        </div>
        <div class="d-flex justify-content-center p-1">
            <span>Drugs:</span>
            <span class="ml-2">${consultation.getDrugs()}</span>
        </div>
        <div class="d-flex justify-content-center p-1">
            <span>Instructions:</span>
            <span class="ml-2">${consultation.getInstruction()}</span>
        </div>
    </div>
</div>

</body>
</html>