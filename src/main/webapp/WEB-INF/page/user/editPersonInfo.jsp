<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/page/user/editPersonInfo.css" type="text/css">
<div class="person-info">
    <div class="person-info-title">
        <div><h4>>>个人信息</h4></div>
    </div>
    <div class="person-info-content">
        <table class="person-table table table-bordered table-hover">
            <thead>
            <tr>
                <th colspan="2" class="text-center">个人信息</th>
            </tr>
            </thead>
            <form action="${pageContext.request.contextPath}/user/editPersonInfo.do" method="post">
                <tr>
                    <td width="50%">ID:</td>
                    <td width="50%"><input type="text" name="userId" value="${user.userId}" required></td>
                </tr>
                <tr>
                    <td>用户名:</td>
                    <td><input type="text" name="loginName" value="${user.loginName}" required></td>
                </tr>
                <tr>
                    <td>真实姓名:</td>
                    <td><input type="text" name="name" value="${user.name}" required></td>
                </tr>
                <tr>
                    <td>角色:</td>
                    <td><input type="text" name="roleId" value="${user.roleId}" required></td>
                </tr>
                <tr>
                    <td>状态:</td>
                    <td>
                        <select name="isActive" required>
                            <option value="1" ${user.isActive=='1'?'selected':''}>启用</option>
                            <option value="2" ${user.isActive=='2'?'selected':''}>停用</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <a href="#"><button class="btn btn-primary btn-info" type="submit">修改</button></a>
                        <a href="${pageContext.request.contextPath}/page/user/personInfo.jsp"><button class="btn btn-primary btn-info" type="button">返回</button></a>
                    </td>
                </tr>
            </form>
        </table>
    </div>
</div>
