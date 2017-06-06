<%--
  Created by IntelliJ IDEA.
  User: XD.Wang
  Date: 2017/6/5
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/base.jsp" %>

<html>
<head>
    <title>用户信息</title>
</head>
<body>
<table>
    <tr>
        <td width="20%">
            <label for="id">用户ID
                <input id="id" type="text" value="${user.id}"/>
            </label>
        </td>
    </tr>
    <tr>
        <td width="20%">
            <label for="nickname">用户昵称
                <input id="nickname" type="text" value="${user.nickname}"/>
            </label>
        </td>
        <td width="20%">
            <label for="email">用户邮箱
                <input id="email" type="text" value="${user.email}"/>
            </label>
        </td>
        <td width="20%">
            <label for="password">用户密码
                <input id="password" type="text" value="${user.password}"/>
            </label>
        </td>
    </tr>
</table>
</body>
</html>
