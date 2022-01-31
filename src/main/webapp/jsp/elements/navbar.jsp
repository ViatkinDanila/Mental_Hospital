<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sc" uri="custom-tags" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<header class="bg-light text-secondary">
    <div class="container-fluid p-0">
        <div class="p-2 d-flex align-items-center justify-content-lg-between">
            <div class="container align-items-center d-flex justify-content-lg-center">
                <a class="mr-4 ml-5 pr-5" href="${pageContext.request.contextPath}/MentalHospital?command=home-page">
                    <img style="width:8rem"
                         src="https://sun1-94.userapi.com/impg/rtxx1D38IgGogEgvRSqWsNfHSXCtbkimRhHlWA/MNlGT-PNr1s.jpg?size=200x0&quality=90&crop=0,0,640,640&sign=322336ead46fdbe67e140d62dde4294b&ava=1">
                </a>
                <span class="text-info h2 mb-0 ml-5 pl-5"><fmt:message key="navbar.address"/></span>
            </div>
            <div class="container d-flex justify-content-lg-around">
                <div>
                    <a type="button"
                       href="${pageContext.request.contextPath}/MentalHospital?command=localization&locale=ru"
                       class="btn btn-primary btn-lg m-2" style="line-height: 1.5">
                        <span class="h2" style="line-height: 1.5">RU</span>
                    </a>
                    <a type="button"
                       href="${pageContext.request.contextPath}/MentalHospital?command=localization&locale=en"
                       class="btn btn-primary btn-lg m-2" style="line-height: 1.5">
                        <span class="h2" style="line-height: 1.5">EN</span>
                    </a>
                </div>
                <div>
                    <sc:access role="GUEST">
                        <a type="button" href="${pageContext.request.contextPath}/MentalHospital?command=login-page"
                           class="btn btn-primary btn-lg my-1 mx-4">
                            <span class="h2" style="line-height: 1.5"><fmt:message key="navbar.login"/></span>
                        </a>
                        <a type="button" href="${pageContext.request.contextPath}/MentalHospital?command=sign-up-page"
                           class="btn btn-primary btn-lg ">
                            <span class="h2" style="line-height: 1.5"><fmt:message key="navbar.signup"/></span>
                        </a>
                    </sc:access>
                    <sc:access role="NOT_GUEST">
                        <a type="button" href="${pageContext.request.contextPath}/MentalHospital?command=profile-page"
                           class="btn btn-success btn-lg my-1 mx-5">
                            <span class="h1" style="line-height: 1.5"><fmt:message key="navbar.profile"/></span>
                        </a>
                        <a type="button" href="${pageContext.request.contextPath}/MentalHospital?command=sign-out"
                           class="btn btn-primary btn-lg my-1 mx-4">
                            <span class="h2" style="line-height: 1.5"><fmt:message key="navbar.sign-out"/></span>
                        </a>
                    </sc:access>
                </div>
            </div>
        </div>
        <div class="py-3 d-flex flex-wrap align-items-center justify-content-around bg-primary h4">
            <div class="container-30 d-flex mr-5">
                <a href="${pageContext.request.contextPath}/MentalHospital?command=home-page"
                   class="nav-link px-5 ml-5 mr-2 text-light">
                    <span class="h1 font-weight-bold" style="line-height: 1.5"><fmt:message
                            key="navbar.doctors"/></span>
                </a>
                <a href="${pageContext.request.contextPath}/MentalHospital?command=login-page"
                   class="nav-link px-5 ml-5 text-light">
                    <span class="h1 font-weight-bold" style="line-height: 1.5"><fmt:message
                            key="navbar.diseases"/></span>
                </a>
            </div>
            <sc:access role="USER">
                <a href="${pageContext.request.contextPath}/MentalHospital?command=consultation-request-page"
                   class="nav-link text-light ml-5">
                    <span class="h1 font-weight-bold" style="line-height: 1.5"><fmt:message
                            key="navbar.request.consultation"/></span>
                </a>
            </sc:access>
            <sc:access role="NOT_USER">
                <div>
                    <span class="h1 font-weight-bold"></span>
                </div>
            </sc:access>
        </div>
    </div>
</header>