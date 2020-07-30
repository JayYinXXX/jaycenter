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
            return true;
        });
    });
</script>

<title>文章管理</title>

<div class="workingArea">

    <%-- 文章列表 --%>
    <div class="panel">
        <%-- 面包屑导航 --%>
        <ul class="breadcrumbs">
            <li><a href="admin_category_list?uid=${c.uid}">所有分类</a></li>
            <li class="current">文章管理</li>
        </ul>
        <h3>当前用户：${u.name}</h3>
        <h3>当前分类：${c.name}</h3>

        <div class="listDataTableDiv">
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>序号</th>
                    <th>标题</th>
                    <th>创建人id</th>
                    <th>创建时间</th>
                    <th>最近更新时间</th>
                    <th>编辑</th>
                    <th>删除</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${as}" var="a">
                    <tr>
                        <td>${a.id}</td>
                        <td>${a.rank}</td>
                        <td>${a.name}</td>
                        <td>${a.uid}</td>
                        <td><fmt:formatDate value="${a.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td><fmt:formatDate value="${a.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td><a href="admin_category_article_edit?id=${a.id}">编辑</a></td>
                        <td><a href="admin_category_article_delete?id=${a.id}" deleteLink="true">删除</a></td>
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

    <%-- 新建文章 --%>
    <div class="panel">
        <div>新增文章</div>
        <div>
            <form id="addForm" action="admin_category_article_add" method="post">
                <input id="name" name="name" type="text" placeholder="文章标题">
                <input type="hidden" name="cid" value="${c.id}">
                <input type="hidden" name="uid" value="${u.id}">
                <button type="submit" class="button">提交</button>
            </form>
        </div>
    </div>

</div>
<%@include file="../include/admin/adminFooter.jsp"%>

