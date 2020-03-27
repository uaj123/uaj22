<%--
  Created by IntelliJ IDEA.
  User: uaj
  Date: 2020/2/9
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="account/findAllAccount">查询所有</a>


    <form action="account/saveAccount" method="post">
        <label>
            账户：<input type="text" name="username"/>
        </label>
        <label>
            密码：<input type="text" name="password"/>
        </label>
        <label>
            余额：<input type="text" name="money"/>
        </label>
        <input type="submit" value="保存"/>
    </form>
</body>
</html>
