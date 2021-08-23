<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="message"/>
<fmt:message key="lable.username" var="username"/>
<fmt:message key="lable.password" var="password"/>
<fmt:message key="placeholder.enter-username" var="enterUsername"/>
<fmt:message key="placeholder.enter-password" var="enterPassword"/>
<fmt:message key="button.login" var="login"/>
<fmt:message key="lable.error" var="error"/>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>TrainingCentertastic</title>
    <link rel="stylesheet" href="static/index-style.css"/>
    <link rel="stylesheet" href="static/language-index-style.css">
    <link rel="stylesheet" href="static/general-style.css">
    <script>
        if ( window.history.replaceState ) {
            window.history.replaceState( null, null, window.location.href );
        }
    </script>
</head>
<body class="login">

<form action="${pageContext.request.contextPath}/controller" method="post">
    <nav class="vertical">
        <input type="hidden" name="command" value="login"/>

        <label for="username"><b>${username}</b></label>
        <input type="text" placeholder="${enterUsername}" name="username" required/><br/>

        <label for="password"><b>${password}</b></label>
        <input type="password" placeholder="${enterPassword}" name="password" required/><br/>

        <c:if test="${errorMessage != null}">
            <h5 class="incorrect-input">
                <br/><br/>${errorMessage}
            </h5>
        </c:if>

        <button type="submit">${login}</button>
    </nav>
    <a href="/controller?command=registrationPage"><fmt:message key="button.registration"/></a>
</form>
<jsp:include page="WEB-INF/view/fragments/language.jsp"/>
</body>
</html>
