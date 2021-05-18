<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="message"/>
<fmt:message key="title.course" var="heading"/>
<html>
<head>
    <title>${heading}</title>
    <link rel="stylesheet" href="static/course-style.css">
</head>
<body>
    <jsp:include page="fragments/header.jsp"/>

    <h3>${nameCourse}</h3>
    <nav>
        <h3>Requirements</h3>
            <c:forEach var="requirement" items="${requirements}">
                    <h5><li>${requirement}</li></h5>
            </c:forEach>

        <%--<c:if test="${sessionScope.role == 'ADMIN'}">
            <table class="table-all">
                <tr>
                    <th><fmt:message key="th.id"/></th>
                    <th><fmt:message key="th.username"/></th>
                </tr>

                <c:forEach var="student" items="${students}">
                    <tr>
                        <td>${student.id}</td>
                        <td>${student.username}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>--%>
    </nav>
</body>
</html>
