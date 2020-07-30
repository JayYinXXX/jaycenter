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
        $("#update-article-form").submit(function () {
            // 名称不为空才能提交
            if (checkEmpty("article-name", "文章标题"))
                return false;
            if (checkEmpty("article-cid", "分类"))
                return false;
            if (checkEmpty("article-rank", "序号"))
                return false;
            return true;
        });
        $("#add-tag-form").submit(function () {
            if (checkEmpty("tag-name", "标签名"))
                return false;
            return true;
        });
    });
</script>

<title>文章编辑</title>

<div class="workingArea">

    <%-- 编辑面板 --%>
    <div class="panel">
        <%-- 面包屑导航 --%>
        <ul class="breadcrumbs">
            <li><a href="admin_category_list?uid=${a.uid}">所有分类</a></li>
            <li><a href="admin_category_article_list?cid=${a.cid}">文章管理</a></li>
            <li class="current">文章编辑</li>
        </ul>

        <%-- 编辑文章信息 --%>
        <div class="panel">
            <h3>文章信息</h3>
            <form id="update-article-form" action="admin_category_article_update" method="post">
                标题：<input id="article-name" type="text" name="name" value="${a.name}">
                <input type="hidden" name="id" value="${a.id}">
                分类：<input id="article-cid" type="text" name="cid" value="${a.cid}">
                序号：<input id="article-rank" type="text" name="rank" value="${a.rank}">
                <input type="hidden" name="uid" value="${a.uid}">
                <input type="hidden" name="isTop" value="${a.top}">
                <input type="hidden" name="type" value="public">
                <input type="hidden" name="version" value="${a.version}">

                <%-- 文章内容 --%>
                <textarea name="articleContent.content" rows="5">${ac.content}</textarea>

                <button type="submit" class="button">提交</button>
            </form>
        </div>

        <%-- 编辑标签信息 --%>
        <div class="panel">
            <h3>文章标签</h3>
            <table>
                <thead>
                <tr>
                    <th>标签名</th>
                    <th>删除</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${ownedTags}" var="tag">
                    <tr>
                        <td>${tag.name}</td>
                        <td><a href="admin_article_tagmap_delete?aid=${a.id}&tid=${tag.id}">删除</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>


            <a href="#" data-dropdown="all-tags" class="button dropdown">选择标签</a>
            <ul id="all-tags" data-dropdown-content class="f-dropdown">
                <c:forEach items="${remainTags}" var="tag">
                    <li><a href="admin_article_tagmap_add?aid=${a.id}&tid=${tag.id}">${tag.name}</a></li>
                </c:forEach>
            </ul>

            <h3>新增标签</h3>
            <form id="add-tag-form" action="admin_article_tag_add" method="post">
                <input id="tag-name" type="text" name="name" placeholder="标签名">
                <input type="hidden" name="uid" value="${a.uid}">
                <input type="hidden" name="aid" value="${a.id}">

                <button type="submit" class="button">提交</button>
            </form>

            <h3>所有标签</h3>
            <table>
                <thead>
                <tr>
                    <th>标签名</th>
                    <th>删除</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${userTags}" var="tag">
                    <tr>
                        <td>${tag.name}</td>
                        <td><a href="admin_article_tag_delete?id=${tag.id}&aid=${a.id}">删除</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>


    </div>


</div>
<%@include file="../include/admin/adminFooter.jsp"%>

