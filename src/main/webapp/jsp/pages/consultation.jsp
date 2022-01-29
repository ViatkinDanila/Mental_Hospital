<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sc" uri="custom-tags" %>

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

<div class="container-fluid d-flex justify-content-center flex-column align-items-center" style="text-decoration: none">
    <div class="w-50 rounded d-flex flex-column mt-4 justify-content-center" style="background-color: #16CAEE">
        <div class="rounded-top" style="background-color: #4f7a9f">
            <div class="d-flex text-light justify-content-between pt-2 px-3 h4">
            <span> <sc:date-formatter date="${consultation.getDate()}"
                                      formatType="${sessionScope.lang}"></sc:date-formatter></span>
                <span>${consultation.getCommunicationType()}</span>
            </div>
        </div>
        <div class="container d-flex w-75 flex-column">
            <div class="d-flex py-2 pt-4 h5" style="margin-left: 32%">
                <span><fmt:message key="consultation.doctor"/></span>
                <a href="${pageContext.request.contextPath}/MentalHospital?command=doctor?id=${consultation.getDoctorId()}"
                   class="font-weight-bold">
                    <span class="ml-2">${consultation.getDoctorFirstName()} ${consultation.getDoctorLastName()}</span>
                </a>
            </div>
            <div class="d-flex py-2 h5" style="margin-left: 32%">
                <span><fmt:message key="consultation.patient"/></span>
                <a href="${pageContext.request.contextPath}/MentalHospital?command=user?id=${consultation.getUserId()}"
                   class="font-weight-bold">
                    <span class="ml-2">${consultation.getPatientFirstName()} ${consultation.getPatientLastName()}</span>
                </a>
            </div>
            <div class="d-flex py-2 pb-3 h5" style="margin-left: 32%">
                <span><fmt:message key="consultation.duration"/></span>
                <span class="ml-2">${consultation.getDuration()} <fmt:message key="units.minutes"/></span>
            </div>
        </div>
        <c:choose>
            <c:when test="${consultation.getConsultationStatus().toString().equals('REJECTED')}">
                <div class="d-flex justify-content-center h3 bg-danger mb-0 rounded-end">
                    <span class="py-2 text-center text-white">${consultation.getConsultationStatus().toString()}</span>
                </div>
            </c:when>
            <c:otherwise>
                <div class="d-flex justify-content-center h3 bg-success mb-0 rounded-end">
                    <span class="py-2 text-center text-white">${consultation.getConsultationStatus().toString()}</span>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <span class="mt-4 h-2 glyphicon glyphicon-arrow-down"></span>

    <c:if test="${consultation.getConsultationStatus().toString().equals('COMPLETED')}">
        <div class="bg-info d-flex flex-column w-50  border  border-dark  rounded mt-4" style="width: 55%">
            <span class="h4 pb-2 pt-3 text-center font-weight-bold"><fmt:message key="consultation.course"/></span>
            <div class="d-flex py-2 pt-1" style="margin-left: 10%">
                <span><fmt:message key="consultation.instructions"/></span>
                <span class="ml-2">${consultation.getInstruction()}</span>
            </div>
            <div class="d-flex py-2" style="margin-left: 10%">
                <div class="d-flex flex-column">
                    <span><fmt:message key="consultation.symptoms-with-diseases"/></span>
                    <c:forEach items="${consultation.getDiseases()}" var="disease" varStatus="counter">
                        <div>
                            <span class="glyphicon glyphicon-pushpin mr-2 ml-3"></span>
                            <span>${disease.getSymptoms()} </span>
                            <span class="glyphicon glyphicon-hand-right mx-2"></span>
                            <a href="${pageContext.request.contextPath}/MentalHospital?command=disease?id=${disease.getId()}"
                               class="text-dark font-weight-bold">
                                    ${disease.getName()}
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="d-flex py-2 pb-3" style="margin-left: 10%">
                <span><fmt:message key="consultation.drugs"/></span>
                <span class="d-flex ml-2">
                     <c:forEach items="${consultation.getDrugs()}" var="drug" varStatus="counter">
                         <c:if test="${counter.index != 0}">
                             <span class="mr-1">,</span>
                         </c:if>
                         <a href="https://www.google.by/search?q=${drug.getName()}+drug" class="text-dark font-weight-bold">
                                 ${drug.getName()}
                         </a>
                         <span>(${drug.getDoze()} mg)</span>
                     </c:forEach>
                </span>
            </div>
        </div>
    </c:if>
</div>

</body>
</html>