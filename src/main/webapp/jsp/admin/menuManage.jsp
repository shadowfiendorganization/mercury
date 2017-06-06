<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/5
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/base.jsp" %>
<html>
<head>
    <title>系统菜单管理</title>
</head>
<body>
<%--表头部分--%>
    <div class="menuManage">
        <table>
            <tbody>
            <tr>
                <td>菜单目录名称：</td>
                <td><input name="menuName" id="menuName" align="left" type="text"/></td>
                <td><button id="serch" class="serchMenu" onclick="javascript:serch()">查询</button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div>
<%--表格区域--%>
<table id="tt"></table>
    </div>
    <div>
        <div id="add" onclick="add();"><i class=""></i>新增</div>
        <div id="upd" onclick="upd();"><i class=""></i>修改</div>
        <div id="del" onclick="del();"><i class=""></i>删除</div>
    </div>
</body>
</html>
