<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="message"/>
<fmt:message key="placeholder.enterUsername" var="enterUsername"/>
<fmt:message key="button.find" var="find"/>
<fmt:message key="lable.findTeacher" var="findTeacher"/>
<fmt:message key="title.students" var="heading"/>
<html>
<head>
    <title>Title</title>
  <link rel="stylesheet" href="static/students-style.css"/>
  <link rel="stylesheet" href="static/language-all-style.css">
  <link rel="stylesheet" href="static/general-style.css">
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
            <td>${teacher.username}</td>
          </tr>
        </c:forEach>
      </table>
      <jsp:include page="fragments/pagination.jsp"/>
    </c:if>

    <nav class="search">
      <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="findTeacher"/>

        <label for="nameTeacher"><b>${findTeacher}</b></label>
        <input type="text" placeholder="${enterUsername}" name="nameTeacher" required/><br/>

        <button type="submit">${find}</button>
        <c:if test="${notExist != null}">
          <br/>${notExist}
        </c:if>
      </form>
      <c:if test="${foundTeacher != null}">
        <table class="table-all">
          <tr>
            <th><fmt:message key="th.username"/></th>
          </tr>
            <tr>
              <td>${foundTeacher}</td>
            </tr>
        </table>
      </c:if>
    </nav>
  </main>

</body>
</html>
