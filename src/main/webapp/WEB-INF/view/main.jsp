<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
</head>
<body>
    <jsp:include page="fragments/header.jsp"/>
    <h1>${welcome} ${name}</h1>
    <h5>${text}</h5>
</body>
</html>
