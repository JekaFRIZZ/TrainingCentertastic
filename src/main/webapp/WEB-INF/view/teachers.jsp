<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="message"/>
<fmt:message key="button.find" var="find"/>
<fmt:message key="lable.findTeacher" var="findTeacher"/>
<fmt:message key="title.students" var="heading"/>
<html>
<head>
    <title>TrainingCentertastic</title>
    <link rel="stylesheet" href="static/students-style.css"/>
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
    <c:if test="${requestScope.teachers != null}">
        <table class="table-all">
            <tr>
                <th><fmt:message key="th.username"/></th>
            </tr>

            <c:forEach var="teacher" items="${teachers}">
                <tr>
                    <td><c:out value="${teacher.username}"/></td>
                </tr>
            </c:forEach>
        </table>
        <jsp:include page="fragments/pagination.jsp"/>
    </c:if>

    <nav class="search">
        <form action="${pageContext.request.contextPath}/controller?command=findTeacher" method="post">
            <input type="hidden" name="command" value="findTeacher"/>

            <div class="findTeacher">
                <label for="nameTeacher"><b>${findTeacher}</b></label>
                <input type="text" placeholder="<fmt:message key="placeholder.enterUsername"/>" name="nameTeacher"
                       required/><br/>
                <button type="submit">${find}</button>
            </div>

            <c:if test="${notExist != null}">
                <br/><c:out value="${notExist}"/>
            </c:if>
        </form>
        <c:if test="${foundTeacher != null}">
            <table class="table-all">
                <tr>
                    <th><fmt:message key="th.username"/></th>
                </tr>
                <tr>
                    <td><c:out value="${foundTeacher}"/></td>
                </tr>
                <tr>
                    <th><fmt:message key="td.taughtCourses"/></th>
                </tr>
                <c:forEach var="course" items="${requestScope.taughtCourses}">
                    <tr>
                        <td>
                            <c:out value="${course.name}"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <form action="${pageContext.request.contextPath}/controller?command=deleteTeacher" method="post">
            <input type="hidden" name="command" value="deleteTeacher"/>

            <div class="deleteTeacher">
                <label for="nameTeacher"><b><fmt:message key="lable.deleteTeacher"/></b></label>
                <input type="text" placeholder="<fmt:message key="placeholder.enterUsername"/>" id="nameTeacher"
                       name="nameTeacher" required/><br/>

                <button type="submit"><fmt:message key="button.delete"/></button>
            </div>
            <c:if test="${deleteMessage != null}">
                <c:out value="${deleteMessage}"/>
            </c:if>
        </form>

        <form action="${pageContext.request.contextPath}/controller?command=createTeacher" method="post">
            <input type="hidden" name="command" value="createTeacher"/>
            <div style="border: 1px solid black; border-radius: 5px; margin-top: 15px; padding: 10px;" class="teachers">
                <label><b><fmt:message key="lable.createdTeacher"/></b></label></br>
                <label for="createNameTeacher"><b><fmt:message key="lable.username"/></b></label>
                <input type="text" placeholder="<fmt:message key="placeholder.enterUsername"/>" id="createNameTeacher"
                       name="nameTeacher" required/><br/>

                <label for="createPasswordTeacher"><b><fmt:message key="lable.password"/></b></label>
                <input type="password" placeholder="<fmt:message key="placeholder.enter-password"/>"
                       id="createPasswordTeacher"
                       name="passwordTeacher" required/><br/>

                <button type="submit" style="margin-top: 1rem;"><fmt:message key="button.create"/></button>
            </div>
            <c:if test="${requestScope.invalidData != null}">
                <h5 class="incorrect-input">
                    <c:out value="${requestScope.invalidData}"/>
                </h5>
            </c:if>
            <c:if test="${requestScope.successCreateTeacher != null}">
                <c:out value="${requestScope.successCreateTeacher}"/>
            </c:if>
            <c:if test="${requestScope.unSuccessCreateTeacher != null}">
                <h5 class="incorrect-input">
                    <c:out value="${requestScope.unSuccessCreateTeacher}"/>
                </h5>
            </c:if>
        </form>
    </nav>
</main>

</body>
</html>
