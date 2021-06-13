<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="message"/>
<fmt:message key="a.courses" var="heading"/>
<fmt:message key="button.go" var="go"/>

<html>
<head>
    <title>${heading}</title>
    <link rel="stylesheet" href="static/courses-style.css">
    <link rel="stylesheet" href="static/language-all-style.css">
    <link rel="stylesheet" href="static/general-style.css">
</head>
<body>
    <jsp:include page="fragments/header.jsp"/>
    <main>
        <nav class="container">
            <c:forEach  var="course" items="${courses}">
                <form class="form" action="${pageContext.request.contextPath}/controller" method="post">
                    <nav class="block">
                        <input type="hidden" name="nameCourse" value="${course.name}"/>
                        <input type="hidden" name="command" value="course"/>

                        <p class="title">${course.name}</p>
                        <img src="" alt="image">
                        <button class="button" type="submit">${go}</button>
                    </nav>
                </form>
            </c:forEach>
        </nav>

        <jsp:include page="fragments/pagination.jsp"/>
    </main>
</body>
</html>
