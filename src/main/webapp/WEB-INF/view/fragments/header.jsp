<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <link rel="stylesheet" href="static/header-style.css">
</head>
<body class="body-header">

    <nav class="topNav">
        <a href="/controller?command=logout"><fmt:message key="a.exit"/></a>
        <c:if test="${sessionScope.role != 'TEACHER'}">
            <a href="/controller?command=courses"><fmt:message key="a.courses"/></a>
        </c:if>
        <a href="/controller?command=mainPage"><fmt:message key="a.main"/></a>
        <c:if test="${sessionScope.role != 'ADMIN'}">
            <a href="/controller?command=myProfileStudent"><fmt:message key="a.myProfile"/></a>
        </c:if>
        <c:if test="${sessionScope.role == 'ADMIN'}">
            <a href="/controller?command=students"><fmt:message key="a.students"/></a>
            <a href="/controller?command=teachers"><fmt:message key="a.teachers"/></a>
        </c:if>
        <div class="changeLanguage">
            <jsp:include page="language.jsp"/>
        </div>
    </nav>
</body>
</html>
