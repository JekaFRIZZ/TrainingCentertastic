<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <title>TrainingCentertastic</title>
    <link rel="stylesheet" href="static/language-all-style.css">
    <link rel="stylesheet" href="static/general-style.css">
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
    <c:if test="${requestScope.incorrectLink != null}">
        ${requestScope.incorrectLink}
    </c:if>
    <nav id="link">
        <p><fmt:message key="td.link"/></p>
        <c:if test="${requestScope.link != null}">
            <c:out value="${requestScope.link}" escapeXml="true"/>
        </c:if>
        <c:if test="${requestScope.link == null}">
            <p><fmt:message key="p.noLink"/></p>
        </c:if>
    </nav>
    <nav id="mark">
        <p><fmt:message key="td.mark"/></p>
        <c:if test="${requestScope.mark == 0}">
            <p><fmt:message key="p.noMark"/></p>
        </c:if>
        <c:if test="${requestScope.mark >=1 && requestScope.mark <=10}">
            ${requestScope.mark}
        </c:if>
    </nav>
    <nav id="review">
        <p><fmt:message key="th.review"/></p>
        <c:if test="${requestScope.review != null}">
            <c:out value="${requestScope.review}" escapeXml="true"/>
        </c:if>
        <c:if test="${requestScope.review == null}">
            <p><fmt:message key="p.noReview"/></p>
        </c:if>
    </nav>
</main>
</body>
</html>
