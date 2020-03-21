<%--
  Created by IntelliJ IDEA.
  User: uaj
  Date: 2020/2/9
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>list</title>
</head>
<body>
    <h1>查询成功</h1>
    <c:forEach items="${accountList}" var="account">
        ${account.username}
    </c:forEach>
</body>
</html>
