<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
</head>
<body class="body-header-index">

    <div class="languages">
        <form class="change-lang" action="${pageContext.request.contextPath}/controller"
              method="post">
            <input class="change-lang-btn" type="submit" value="ru"/>
            <input type="hidden" name="local" value="ru_RU"/>
            <input type="hidden" name="command" value="changeLanguage"/>
            <input type="hidden" name="currentPage" value="${param.get("command")}" />
            <input type="hidden" name="page" value="${param.get("page")}"/>
            <input type="hidden" name="nameCourse" value="<c:out value="${nameCourse}"/>">
        </form>

        <form class="change-lang" action="${pageContext.request.contextPath}/controller"
              method="post">
            <input class="change-lang-btn" type="submit" value="by"/>
            <input type="hidden" name="local" value="by_BY"/>
            <input type="hidden" name="command" value="changeLanguage"/>
            <input type="hidden" name="currentPage" value="${param.get("command")}"/>
            <input type="hidden" name="page" value="${param.get("page")}"/>
            <input type="hidden" name="nameCourse" value="<c:out value="${nameCourse}"/>">
        </form>

        <form class="change-lang" action="${pageContext.request.contextPath}/controller"
              method="post">
            <input class="change-lang-btn" type="submit" value="en"/>
            <input type="hidden" name="local" value="en_US"/>
            <input type="hidden" name="command" value="changeLanguage"/>
            <input type="hidden" name="currentPage" value="${param.get("command")}"/>
            <input type="hidden" name="page" value="${param.get("page")}"/>
            <input type="hidden" name="nameCourse" value="<c:out value="${nameCourse}"/>">
        </form>
    </div>
</body>
</html>
