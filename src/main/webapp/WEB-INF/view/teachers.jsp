<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="message"/>
<fmt:message key="placeholder.enterId" var="enterId"/>
<fmt:message key="button.find" var="find"/>
<fmt:message key="lable.findStudent" var="findStudent"/>
<fmt:message key="title.students" var="heading"/>
<html>
<head>
    <title>Title</title>
  <link rel="stylesheet" href="static/students-style.css"/>
  <link rel="stylesheet" href="static/language-all-style.css">
</head>
<body>
  <jsp:include page="fragments/header.jsp"/>
  <main>
    <table class="table-all">
      <tr>
        <th><fmt:message key="th.id"/></th>
        <th><fmt:message key="th.username"/></th>
      </tr>

      <c:forEach var="teacher" items="${teachers}">
        <tr>
          <td>${teacher.id}</td>
          <td>${teacher.username}</td>
        </tr>
      </c:forEach>
    </table>
    <jsp:include page="fragments/pagination.jsp"/>
  </main>

</body>
</html>
