<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="message"/>
<fmt:message key="lable.username" var="username"/>
<fmt:message key="lable.password" var="password"/>
<fmt:message key="placeholder.enter-username" var="enterUsername"/>
<fmt:message key="placeholder.enter-password" var="enterPassword"/>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>TrainingCentertastic</title>
    <link rel="stylesheet" href="static/index-style.css"/>
    <link rel="stylesheet" href="static/language-index-style.css">
    <link rel="stylesheet" href="static/general-style.css">
    <script>
        if (window.history.replaceState) {
            window.history.replaceState(null, null, window.location.href);
        }
    </script>
</head>
<body class="login">
<form action="${pageContext.request.contextPath}/controller?command=registration" method="post"
      style="position: relative">
    <nav class="vertical">
        <input type="hidden" name="command" value="registration"/>

        <label for="username"><b>${username}</b></label>
        <input type="text" placeholder="${enterUsername}" name="username" id="username" required/><br/>

        <label for="firstPassword"><b>${password}</b></label>
        <input type="password" placeholder="${enterPassword}" name="firstPassword" id="firstPassword" required/><br/>

        <label for="secondPassword" style="position: absolute;
    top: 95px;
    left: 1px;"><b>${password}</b></label>
        <input type="password" placeholder="Подтвердите пароль" name="secondPassword" id="secondPassword"
               required/><br/>

        <button type="submit"><fmt:message key="button.registration"/></button>
    </nav>
    <nav>
        <c:if test="${requestScope.invalidName != null}">
            <c:out value="${requestScope.invalidName}"/>
        </c:if>
    </nav>
    <nav>
        <c:if test="${requestScope.errorMessage != null}">
            <h5 class="incorrect-input">
                <br/><br/><c:out value="${errorMessage}"/>
            </h5>
        </c:if>
    </nav>
    <nav>
        <a href="/controller?command=login"><fmt:message key="a.login"/></a>
    </nav>
</form>
<jsp:include page="fragments/language.jsp"/>
</body>
</html>
