<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
    </head>
    <body class="login">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <nav class="vertical">
                <input type="hidden" name="command" value="registration"/>

                <label for="username"><b>${username}</b></label>
                <input type="text" placeholder="${enterUsername}" name="username" required/><br/>

                <label for="firstPassword"><b>${password}</b></label>
                <input type="password" placeholder="${enterPassword}" name="firstPassword" required/><br/>

                <label for="secondPassword"><b>${password}</b></label>
                <input type="password" placeholder="Подтвердите пароль" name="secondPassword" required/><br/>

                <c:if test="${requestScope.errorMessage != null}">
                    <h5 class="incorrect-input">
                        <br/><br/>${errorMessage}
                    </h5>
                </c:if>

                <button type="submit">registration</button>
            </nav>
        </form>
        <jsp:include page="fragments/language.jsp"/>
    </body>
</html>
