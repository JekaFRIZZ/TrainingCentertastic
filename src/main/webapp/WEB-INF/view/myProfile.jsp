<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="message"/>
<fmt:message key="a.myProfile" var="heading"/>
<fmt:message key="button.go" var="go"/>
<html>
<head>
    <title>${heading}</title>
    <link rel="stylesheet" href="static/courses-style.css">
    <link rel="stylesheet" href="static/language-all-style.css">
    <link rel="stylesheet" href="static/general-style.css">
    <script>
        if ( window.history.replaceState ) {
            window.history.replaceState( null, null, window.location.href );
        }
    </script>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<main>
    <nav class="container">
        <c:if test="${sessionScope.role == 'STUDENT'}">
            <input type="hidden" name="command" value="myProfileStudent"/>

            <c:forEach var="course" items="${courses}">
                <form class="form" action="${pageContext.request.contextPath}/controller?command=studyCourse" method="post">
                    <nav class="block">
                        <c:set var="nameCourse" value="${course.name}"/>
                        <input type="hidden" name="nameCourse" value="${nameCourse}"/>
                        <input type="hidden" name="command" value="studyCourse"/>

                        <p class="title"><c:out value="${course.name}"/></p>
                        <button class="button" type="submit">${go}</button>
                    </nav>
                </form>
            </c:forEach>
        </c:if>
        <c:if test="${sessionScope.role == 'TEACHER'}">
            <c:forEach var="course" items="${courses}">
                <form class="form" action="${pageContext.request.contextPath}/controller?command=subjectTaught" method="post">
                    <nav class="block">
                        <input type="hidden" name="command" value="subjectTaught">
                        <c:set var="nameCourse" value="${course.name}"/>
                        <input type="hidden" name="nameCourse" value="${nameCourse}"/>

                        <p class="title"><c:out value="${course.name}"/></p>
                        <button class="button" type="submit">${go}</button>
                    </nav>
                </form>
            </c:forEach>
        </c:if>
    </nav>

    <jsp:include page="fragments/pagination.jsp"/>
</main>
</body>
</html>
