<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="message"/>
<fmt:message key="a.exit" var="exit"/>
<fmt:message key="a.courses" var="courses"/>
<fmt:message key="a.main" var="main"/>
<fmt:message key="a.myProfile" var="myProfile"/>
<fmt:message key="a.students" var="students"/>
<fmt:message key="a.myCourses" var="myCourses"/>
<html>
<head>
    <link rel="stylesheet" href="static/header-style.css">
</head>
<body class="body-header">

    <nav class="topNav">
        <a href="/controller?command=logout">${exit}</a>
        <c:if test="${sessionScope.role != 'TEACHER'}">
            <a href="/controller?command=courses">${courses}</a>
        </c:if>
        <a href="/controller?command=mainPage">${main}</a>
        <c:if test="${sessionScope.role != 'ADMIN'}">
            <a href="/controller?command=myProfileStudent">${myProfile}</a>
        </c:if>
        <c:if test="${sessionScope.role == 'ADMIN'}">
            <a href="/controller?command=students">${students}</a>
            <a href="/controller?command=teachers">Teachers</a>
        </c:if>
        <div class="changeLanguage">
            <jsp:include page="language.jsp"/>
        </div>
    </nav>
</body>
</html>
