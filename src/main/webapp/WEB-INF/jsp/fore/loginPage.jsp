<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<%@include file="../include/fore/foreHeader.jsp"%>

<script>
    $(function () {
        // 表单提交检测
        $("#loginForm").submit(function () {
            // 不为空才能提交
            if(checkEmpty("name", "账户名"))
                return false;
            if(checkEmpty("password", "密码"))
                return false;
            return true;
        });
    });
</script>

<title>登陆</title>
<div class="workingArea">

    <div class="panel">
        <h4>${message}</h4>
        <form id="loginForm" action="login" method="post">
            <input id="name" name="name" placeholder="账户名">
            <input id="password" name="password" placeholder="账户密码">
            <button id="loginSubmit" class="button" type="submit">登陆</button>
        </form>
    </div>



</div>
<%@include file="../include/fore/foreFooter.jsp"%>