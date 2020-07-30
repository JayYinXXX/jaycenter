<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
        isELIgnored="false" import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<%--jsp参数
        u - User
        cs - List<Category>
--%>

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
            return true;
        });
    });
</script>

<title>分类管理</title>

<div class="workingArea">

    <%-- 分类面板 --%>
    <div class="panel">
        <%-- 面包屑导航 --%>
        <ul class="breadcrumbs">
            <li class="current">所有分类</li>
        </ul>
        <h3>当前用户：${u.name}</h3>

        <div class="listDataTableDiv">
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>序号</th>
                    <th>分类名称</th>
                    <th>文章管理</th>
                    <th>编辑</th>
                    <th>删除</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${cs}" var="c">
                    <tr>
                        <td>${c.id}</td>
                        <td>${c.rank}</td>
                        <td>${c.name}</td>
                        <td><a href="admin_category_article_list?cid=${c.id}">文章</a></td>
                        <td><a href="admin_category_edit?id=${c.id}">编辑</a></td>
                        <td><a href="admin_category_delete?id=${c.id}" deleteLink="true">删除</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <%-- 分页 --%>
        <div class="pageDiv">
            <%@include file="../include/admin/adminPage.jsp"%>
        </div>
    </div>

    <%-- 新增分类 --%>
    <div class="panel">
        <div>新增分类</div>
        <div>
            <form id="addForm" action="admin_category_add" method="post">
                <input id="name" name="name" type="text" placeholder="分类名称">
                <input type="hidden" name="uid" value="${u.id}">
                <button type="submit" class="button">提交</button>
            </form>
        </div>
    </div>

</div>
<%@include file="../include/admin/adminFooter.jsp"%>

