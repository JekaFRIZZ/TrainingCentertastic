<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="message"/>
<fmt:message key="title.course" var="heading"/>
<fmt:message key="button.edit" var="edit"/>
<fmt:message key="button.submit" var="submitText"/>
<fmt:message key="placeholder.enterNewRequirement" var="enterRequirement"/>
<html>
<head>
    <title>${heading}</title>
    <link rel="stylesheet" href="static/course-style.css">
    <link rel="stylesheet" href="static/language-all-style.css">
    <link rel="stylesheet" href="static/general-style.css">
</head>
<body>
    <jsp:include page="fragments/header.jsp"/>
    <main>
        <nav class="nameCourse">
            <h3>${nameCourse}</h3>
        </nav>
        <nav>
            <c:if test="${submit == null}">
                <h3 class="requirement-title"><fmt:message key="h3.requirements"/></h3>
            </c:if>
            <nav class="requirements">
                <c:forEach var="requirement" items="${requirements}">
                        <h5><li>${requirement}</li></h5>
                </c:forEach>
            </nav>

            <c:if test="${sessionScope.role == 'ADMIN'}">
                <table class="table-all">
                    <tr>
                        <th><fmt:message key="th.username"/></th>
                    </tr>

                    <c:forEach var="student" items="${students}">
                        <tr>
                            <td>${student.username}</td>
                        </tr>
                    </c:forEach>
                </table>

                <nav class="edit-nav">
                    <form action="${pageContext.request.contextPath}/controller" method="post" class="edit-form">
                        <input type="hidden" name="nameCourse" value="${nameCourse}">
                        <input type="hidden" name="command" value="newRequirement"/>
                        <label for="newRequirement"><fmt:message key="lable.requirement"/> </label>
                        <textarea id="newRequirement" name="newRequirement" rows="5" cols="50"></textarea>
                        <button type="submit">${edit}</button>
                    </form>
                </nav>

                <nav>
                    <c:if test="${successRequirement != null}">
                        ${successRequirement}
                    </c:if>
                </nav>
            </c:if>
            <c:if test="${sessionScope.role == 'STUDENT'}">
                <c:if test="${submit == null}">
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="command" value="submitStudent">
                        <input type="hidden" name="nameCourse" value="${requestScope.nameCourse}">
                        <button type="submit">${submitText}</button>
                    </form>
                </c:if>

                <nav>
                    <c:if test="${submit != null}">
                        ${submit}
                    </c:if>
                </nav>
            </c:if>
        </nav>
    </main>
</body>
</html>
