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
    <script>
        if (window.history.replaceState) {
            window.history.replaceState(null, null, window.location.href);
        }
    </script>
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
                        <input type="hidden" name="studentName" value="${student.username}">
                        <input type="hidden" name="nameCourse" value="${nameCourse}">
                        <lable>${student.username}</lable>
                        <button><fmt:message key="button.go"/></button>
                    </form>
                </td>
            </tr>
        </c:forEach>

    </table>
    <form action="${pageContext.request.contextPath}/controller?command=createTask" method="post" style="display: flex;
    justify-content: center;">
        <input type="hidden" name="command" value="createTask">
        <input type="hidden" name="nameCourse" value="${nameCourse}">
        <div class="taskName">
            <input type="text" placeholder="<fmt:message key="placeholder.enter-task-name"/>" name="taskName" required>
            <input type="text" placeholder="<fmt:message key="placeholder.enter-task-assignment"/>" name="assignment"
                   required>
            <button><fmt:message key="button.create"/></button>
        </div>
    </form>
    <nav>
        <c:if test="${requestScope.notUniqueName != null}">
            <h5 class="incorrect-input">
                <c:out value="${requestScope.notUniqueName}"/>
            </h5>
        </c:if>
    </nav>
</main>
</body>
</html>
