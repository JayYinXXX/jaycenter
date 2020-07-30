<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
        isELIgnored="false" import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="../include/fore/foreHeader.jsp"%>
<%@include file="../include/fore/foreNavigator.jsp"%>

<style>
    <%-- 主页顶部区块 --%>
    #homeTop{
        position: relative;
        background-image: url("img/${u.id}/home.jpg");
        background-position: center;
        background-size: cover;
        background-color: #eeaaaa;
        height: 100vh;
    }
    #huafeichi {
        position: absolute;
        width: 25rem;
        left: 5rem;
        top: 5rem;
    }
</style>


<title>${u.nickname}的主页</title>
<div class="workingArea">

    <%-- 模态框组件 --%>
    <%-- 设置浏览模式 --%>
    <c:set var="pattern" value="edit"/>
    <%@include file="../include/fore/modal.jsp"%>

    <%-- 主页顶部区块 --%>
    <div id="homeTop">
    </div>

    <%--  --%>

</div>
<%@include file="../include/fore/foreFooter.jsp"%>