<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<%@include file="../include/fore/foreHeader.jsp"%>

<script>
    $(function () {
        // 表单提交检测
        $("#registerForm").submit(function () {
            // 不为空才能提交
            if(checkEmpty("name", "账户名"))
                return false;
            if(checkEmpty("password", "密码"))
                return false;
            if(checkEmpty("nickname", "昵称"))
                return false;
            return true;
        });
    });
</script>

<title>注册</title>
<div class="workingArea">

    <div class="panel">
        <form id="registerForm" action="register" method="post">
            <input id="name" name="name" placeholder="账户名">
            <input id="password" name="password" placeholder="账户密码">
            <input id="nickname" name="nickname" placeholder="昵称">
            <button id="registerSubmit" class="button" type="submit">注册</button>
        </form>
    </div>



</div>
<%@include file="../include/fore/foreFooter.jsp"%>