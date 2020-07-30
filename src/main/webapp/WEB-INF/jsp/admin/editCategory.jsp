<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
        isELIgnored="false" import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<script>
    // 点亮指定导航
    $(function () {
        $("#navUser").addClass("active");
    });

    $(function () {
        // 表单提交
        $("#addForm").submit(function () {
            // 名称不为空才能提交
            if(checkEmpty("name", "分类名称"))
                return false;
            if(checkEmpty("rank", "序号"))
                return false;
            return true;
        });
    });
</script>

<title>编辑分类</title>

<div class="workingArea">

    <%-- 面板 --%>
    <div class="panel">
        <%-- 面包屑导航 --%>
        <ul class="breadcrumbs">
            <li><a href="admin_category_list?uid=${c.uid}">所有分类</a></li>
            <li class="current">编辑分类</li>
        </ul>

        <%-- 编辑分类 --%>
        <div class="panel">
            <div>编辑分类</div>
            <div>
                <form id="addForm" action="admin_category_update" method="post">
                    名称：<input id="name" name="name" type="text" value="${c.name}">
                    序号：<input id="rank" name="rank" type="text" value="${c.rank}">
                    <input type="hidden" name="id" value="${c.id}">
                    <input type="hidden" name="uid" value="${c.uid}">
                    <button type="submit" class="button">提交</button>
                </form>
            </div>
        </div>

    </div>


</div>
<%@include file="../include/admin/adminFooter.jsp"%>

