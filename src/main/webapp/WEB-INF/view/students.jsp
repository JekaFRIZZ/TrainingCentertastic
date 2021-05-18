<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="message"/>
<fmt:message key="placeholder.enterId" var="enterId"/>
<fmt:message key="button.find" var="find"/>
<fmt:message key="lable.findStudent" var="findStudent"/>
<fmt:message key="title.students" var="heading"/>
<html>
<head>
    <title>${heading}</title>
    <link rel="stylesheet" href="static/students-style.css"/>
</head>
<body>
    <jsp:include page="fragments/header.jsp"/>

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
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="findStudent"/>

        <label for="idStudent"><b>${findStudent}</b></label>
        <input type="text" placeholder="${enterId}" name="idStudent" required/><br/>

        <button type="submit">${find}</button>

    </form>
    <c:if test="${review != null}">
        <table class="table-one">
            <tr>
                <th><fmt:message key="th.id"/></th>
                <th><fmt:message key="th.username"/></th>
                <th><fmt:message key="th.review"/></th>
            </tr>
            <tr>
                <td>${id}</td>
                <td>${username}</td>
                <td>${review}</td>
            </tr>
        </table>
    </c:if>
</body>
</html>
