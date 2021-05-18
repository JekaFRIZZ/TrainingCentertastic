<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="message"/>
<fmt:message key="a.courses" var="heading"/>

<html>
<head>
    <title>${heading}</title>
    <link rel="stylesheet" href="static/courses-style.css">
</head>
<body>
    <jsp:include page="fragments/header.jsp"/>

    <main>
        <nav class="container">
            <c:forEach  var="course" items="${courses}">
                <form class="form" action="${pageContext.request.contextPath}/controller" method="post">
                    <nav class="block">
                        <c:set var="nameCourse" value="${course.name}"/>
                        <input type="hidden" name="nameCourse" value="${nameCourse}"/>
                        <input type="hidden" name="command" value="course"/>

                        <p class="title">${course.name}</p>
                        <img src="" alt="image">
                        <button class="button" type="submit">Перейти</button>
                    </nav>
                </form>
            </c:forEach>
        </nav>

        <table cellpadding="5" cellspacing="5">
            <tr>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="/controller?command=courses&page=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
        </table>
    </main>


</body>
</html>
