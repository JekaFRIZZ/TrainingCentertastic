<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <title>TrainingCentertastic</title>
    <link rel="stylesheet" href="static/subjectTaught-style.css"/>
    <link rel="stylesheet" href="static/language-all-style.css">
    <link rel="stylesheet" href="static/general-style.css">
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<main>
    <table class="table-all">
        <tr>
            <td><fmt:message key="td.taskName"/></td>
            <td><fmt:message key="title.course"/></td>
            <td><fmt:message key="td.link"/></td>
            <td><fmt:message key="td.mark"/></td>
            <td><fmt:message key="th.review"/></td>
        </tr>
        <c:forEach var="homework" items="${homeworks}">
            <tr>
                <td>${homework.taskName}</td>
                <td>${homework.courseName}</td>
                <td><a href="${homework.link}">${homework.link}</a></td>
                <td>
                    <form action="${pageContext.request.contextPath}/controller?command=taskViewer" method="post">
                        <input type="hidden" name="studentName" value="${studentName}">
                        <input type="hidden" name="nameCourse" value="${nameCourse}">
                        <input type="hidden" name="taskId" value="${homework.id}">
                        <input type="number" name="mark" value="${homework.mark}" min="0" max="10" step="1">
                        <button type="submit"><fmt:message key="button.estimate"/></button>
                    </form>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/controller?command=taskViewer" method="post">
                        <input type="hidden" name="username" value="${username}">
                        <input type="hidden" name="nameCourse" value="${nameCourse}">
                        <input type="hidden" name="taskId" value="${homework.id}">
                        <input type="text" value="<c:out value="${homework.review}" escapeXml="true"/>" name="review">
                        <button type="submit"><fmt:message key="button.comment"/></button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${requestScope.incorrectMark != null}">
        ${requestScope.incorrectMark}
    </c:if>
</main>
</body>
</html>
