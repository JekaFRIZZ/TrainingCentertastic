<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="message"/>
<fmt:message key="placeholder.enterUsername" var="enterUsername"/>
<fmt:message key="button.find" var="find"/>
<fmt:message key="lable.findStudent" var="findStudent"/>
<fmt:message key="title.students" var="heading"/>
<html>
<head>
    <title>${heading}</title>
    <link rel="stylesheet" href="static/students-style.css"/>
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
    <c:if test="${requestScope.students != null}">
        <table class="table-all">
            <tr>
                <th><fmt:message key="th.username"/></th>
            </tr>

            <c:forEach var="student" items="${students}">
                <tr>
                    <td><c:out value="${student.username}"/></td>
                </tr>
            </c:forEach>
        </table>
        <jsp:include page="fragments/pagination.jsp"/>
    </c:if>

    <nav class="search">
        <form action="${pageContext.request.contextPath}/controller?command=findStudent" method="post">
            <div class="nameStudent">
                <input type="hidden" name="command" value="findStudent"/>

                <label for="nameStudent"><b>${findStudent}</b></label>
                <input type="text" placeholder="${enterUsername}" name="nameStudent" required/><br/>

                <button type="submit">${find}</button>
            </div>
        </form>
        <c:if test="${notExist != null}">
            <br/>${notExist}
        </c:if>
        <c:if test="${name != null}">
            <table class="table-all">
                <tr>
                    <th><fmt:message key="th.username"/></th>
                </tr>
                <tr>
                    <td>${name}</td>
                </tr>
                <tr>
                    <th><fmt:message key="th.coursesStudied"/></th>
                </tr>
                <c:forEach var="course" items="${requestScope.courses}">
                    <tr>
                        <td>
                            <c:out value="${course.name}"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </nav>
</main>
</body>
</html>
