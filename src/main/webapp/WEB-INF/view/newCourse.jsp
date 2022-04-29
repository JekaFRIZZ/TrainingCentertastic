<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <title>TrainingCentertastic</title>
    <link rel="stylesheet" href="static/style.css">
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
    <form action="${pageContext.request.contextPath}/controller?command=newCourse" method="post">
        <input type="hidden" name="command" value="newCourse"/>
        <div style="margin-bottom: 15px;">
            <label for="newCourseName"><fmt:message key="th.name"/></label>
            <input type="text" placeholder="<fmt:message key="placeholder.enter-course-name"/>" id="newCourseName"
                   name="newCourseName" required/></br>
        </div>
        <textarea id="newCourseRequirement" name="newCourseRequirement"
                  placeholder="<fmt:message key="placeholder.enter-course-requirement"/>" rows="5" cols="50"
                  required></textarea>
        <div style="margin-bottom: 15px;">
            <label for="teachers"><fmt:message key="lable.assign-teacher"/></label>
            <select name="teachers" id="teachers">
                <c:forEach var="teacher" items="${teachers}">
                    <option value="${teacher.username}"><c:out value="${teacher.username}"/></option>
                </c:forEach>
            </select>
        </div>
        <button type="submit"><fmt:message key="button.create"/></button>
    </form>
    <c:if test="${requestScope.invalidName != null}">
        <h5 class="incorrect-input">
            <c:out value="${requestScope.invalidName}"/>
        </h5>
    </c:if>
    <c:if test="${requestScope.notUniqueCourseName != null}">
        <h5 class="incorrect-input">
            <c:out value="${requestScope.notUniqueCourseName}"/>
        </h5>
    </c:if>
    <c:if test="${requestScope.courseNotCreated != null}">
        <h5 class="incorrect-input">
            <c:out value="${requestScope.courseNotCreated}"/>
        </h5>
    </c:if>
    <c:if test="${requestScope.successCreateCourse != null}">
        <c:out value="${requestScope.successCreateCourse}"/>
    </c:if>
</main>
</body>
</html>