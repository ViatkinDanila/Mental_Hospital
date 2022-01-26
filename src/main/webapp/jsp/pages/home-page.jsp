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

<div class="container d-flex flex-column">
    <span class="h5 text-dark m-auto p-5">HOME PAGE</span>
    <a class="h3 m-auto p-5" href="${pageContext.request.contextPath}/MentalHospital?command=goto-consultation-page&id=1">Get consultation by id=1</a>
</div>

</body>
</html>