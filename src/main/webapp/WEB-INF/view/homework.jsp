<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="message"/>
<fmt:message key="title.course" var="course"/>
<fmt:message key="td.link" var="link"/>
<fmt:message key="td.mark" var="mark"/>
<fmt:message key="th.review" var="review"/>
<fmt:message key="button.estimate" var="estimate"/>
<fmt:message key="button.comment" var="comment"/>
<html>
<head>
    <title>TrainingCentertastic</title>
    <link rel="stylesheet" href="static/subjectTaught-style.css"/>
    <link rel="stylesheet" href="static/language-all-style.css">
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<main>
    <table class="table-all">
        <tr>
            <td><fmt:message key="lable.username"/></td>
            <td>${course}</td>
            <td>${link}</td>
            <td>${mark}</td>
            <td>${review}</td>
        </tr>
        <c:forEach var="homework" items="${sessionScope.homeworks}">
            <tr>
                <td>${homework.username}</td>
                <td>${homework.courseName}</td>
                <td>${homework.link}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/controller?command=taskViewer" method="post">
                        <input type="hidden" name="username" value="${username}">
                        <input type="hidden" name="nameCourse" value="${nameCourse}">
                        <input type="hidden" name="taskId" value="${homework.id}">
                        <input type="number" name="mark" value="${homework.mark}" min="0" max="10" step="1">
                        <button type="submit">${estimate}</button>
                    </form>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/controller?command=taskViewer" method="post">
                        <input type="hidden" name="username" value="${username}">
                        <input type="hidden" name="nameCourse" value="${nameCourse}">
                        <input type="hidden" name="taskId" value="${homework.id}">
                        <input type="text" value="${homework.review}" name="review">
                        <button type="submit">${comment}</button>
                    </form>
                </td>

            </tr>
        </c:forEach>
    </table>
</main>
</body>
</html>
