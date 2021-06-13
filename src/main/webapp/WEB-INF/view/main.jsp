<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/timeTag.tld" prefix="ct"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="message"/>
<fmt:message key="h1.welcome" var="welcome"/>
<fmt:message key="h5.text" var="text"/>
<fmt:message key="a.main" var="heading"/>
<html>
<head>
    <title>${heading}</title>
    <link rel="stylesheet" href="static/style.css">
    <link rel="stylesheet" href="static/language-all-style.css">
    <link rel="stylesheet" href="static/general-style.css">
</head>
<body>
    <jsp:include page="fragments/header.jsp"/>
    <main>
        <h1>${welcome} ${sessionScope.username}</h1>
        <h3><ct:time/></h3>
        <img src="<c:url value="/image/mainPicture.jpg"/>" width="100%" height="100%"/>
    </main>
</body>
</html>