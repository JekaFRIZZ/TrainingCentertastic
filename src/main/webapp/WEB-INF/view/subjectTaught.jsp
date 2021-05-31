<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="message"/>
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
                <td>
                    <fmt:message key="th.name"/>
                </td>
            </tr>
            <c:forEach var="student" items="${students}">
                <tr>
                    <td>
                        <form action="${pageContext.request.contextPath}/controller?command=taskViewer" method="post">
                            <input type="hidden" name="username" value="${student.username}">
                            <input type="hidden" name="nameCourse" value="${nameCourse}">
                            <lable>${student.username}</lable>
                            <button><fmt:message key="button.go"/></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </main>
</body>
</html>
