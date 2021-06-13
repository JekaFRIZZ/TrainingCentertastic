<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <title>TrainingCentertastic</title>
    <link rel="stylesheet" href="static/studyCourse-style.css"/>
    <link rel="stylesheet" href="static/language-all-style.css">
    <link rel="stylesheet" href="static/general-style.css">
</head>
<body>
    <jsp:include page="fragments/header.jsp"/>
    <main>
        <table class="table-all">
            <tr>
                <th><fmt:message key="th.name"/></th>
            </tr>

            <c:forEach var="task" items="${tasks}">
                <tr>
                    <td>
                        <form action="${pageContext.request.contextPath}/controller?command=task" method="post">
                            <c:set var="taskName" value="${task.taskName}"/>
                            <input type="hidden" name="taskName" value="${taskName}"/>
                            <input type="hidden" name="nameCourse" value="${task.courseName}"/>
                            <input type="hidden" name="id" value="${task.id}"/>
                            <label>${taskName}</label>
                            <button name="button" type="submit"><fmt:message key="button.go"/></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </main>
</body>
</html>
