<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sc" uri="custom-tags" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<header class="bg-light text-secondary ">
    <div class="container-fluid p-0">
        <div class="p-2 d-flex align-items-center justify-content-lg-between">
            <div class="container align-items-center d-flex justify-content-lg-center">
                <img class="mr-5" style="width:7%"
                     src="https://sun1-94.userapi.com/impg/rtxx1D38IgGogEgvRSqWsNfHSXCtbkimRhHlWA/MNlGT-PNr1s.jpg?size=200x0&quality=90&crop=0,0,640,640&sign=322336ead46fdbe67e140d62dde4294b&ava=1">
                <span class="text-info ml-5">Budagovo, Yana Chechota 4, 46</span>
            </div>
            <div class="container d-flex justify-content-lg-around">
                <div>
                    <a type="button"
                       href="${pageContext.request.contextPath}/MentalHospital?command=localization&locale=ru"
                       class="btn btn-primary m-1 langToggle" data-onstyle="light">RU</a>
                    <a type="button"
                       href="${pageContext.request.contextPath}/MentalHospital?command=localization&locale=en"
                       class="btn btn-primary m-1 langToggle" data-onstyle="light">EN</a>
                </div>
                <div>
                    <a type="button" href="${pageContext.request.contextPath}/MentalHospital?command=login-page"
                       class="btn btn-primary my-1 mx-3">Login</a>
                    <a type="button" href="${pageContext.request.contextPath}/MentalHospital?command=sign-up-page"
                       class="btn btn-primary">Sign up</a>
                </div>
            </div>
        </div>
        <div class="py-4 d-flex flex-wrap align-items-center justify-content-around bg-primary h4">
            <div class="container-30 d-flex mr-5">
                <a href="${pageContext.request.contextPath}/MentalHospital?command=home-page"
                   class="nav-link px-5 text-light">Doctors</a>
                <a href="${pageContext.request.contextPath}/MentalHospital?command=login-page"
                   class="nav-link px-5 text-light">Diseases</a>
            </div>
            <a href="${pageContext.request.contextPath}/MentalHospital?command=login-page"
               class="nav-link text-light ml-5">Order</a>
        </div>
    </div>
</header>