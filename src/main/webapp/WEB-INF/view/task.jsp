<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <title>TrainingCentertastic</title>
    <link rel="stylesheet" href="static/language-all-style.css">
</head>
<body>
    <jsp:include page="fragments/header.jsp"/>
    <main>
        Задание : ${sessionScope.assignment}
        <form action="${pageContext.request.contextPath}/controller?command=changeLink" method="post">
            <input type="hidden" name="taskName" value="${taskName}">
            <input type="text" name="link" required>
            <button type="submit"><fmt:message key="button.send"/></button>
        </form>
    </main>
</body>
</html>
