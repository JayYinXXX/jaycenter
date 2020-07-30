<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
        isELIgnored="false" import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<%--jsp参数
        u - User
        d - Diary
        dcs - List<DiaryContent>
        dis - List<DiaryImage>
--%>

<script>
    $(function () {
        // 点亮指定导航
        $("#navUser").addClass("active");

        // 表单提交
        $("#addForm").submit(function () {
            // 名称不为空才能提交
            if (checkEmpty("content", "日记内容"))
                return false;
            if (!checkInt("index", "序号"))
                return false;
            return true;
        });
        // 图片表单
        $("#addImageForm").submit(function () {
            if (checkEmpty("image", "图片"))
                return false;
            return true;
        });
    });
</script>

<title>日记内容</title>

<div class="workingArea">

    <%-- 日记内容列表 --%>
    <div class="panel">
        <%-- 面包屑导航 --%>
        <ul class="breadcrumbs">
            <li><a href="admin_diary_list?uid=${u.id}">所有日记</a></li>
            <li class="current">编辑日记</li>
        </ul>
        <h3>当前用户：${u.name}</h3>
        <h3>当前日记：${d.name}</h3>

        <div class="listDataTableDiv">
            <table>
                <thead>
                <tr>
                    <th>序号</th>
                    <th>类型</th>
                    <th>编辑</th>
                    <th>删除</th>
                    <th>内容</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${dcs}" var="dc">
                    <tr>
                        <td>${dc.index}</td>
                        <td>${dc.type}</td>
                        <td><a href="admin_diary_content_edit?id=${dc.id}">编辑</a></td>
                        <td><a href="admin_diary_content_delete?id=${dc.id}" deleteLink="true">删除</a></td>
                        <td>${dc.content}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

    </div>

    <%-- 新增日记项 --%>
    <div class="panel">
        <div>新增日记项</div>
        <div>
            <form id="addForm" action="admin_diary_content_add" method="post">
                <input id="content" name="content" type="text" placeholder="内容">
                <input id="index" name="index" type="text" placeholder="序号">
                <input type="hidden" name="did" value="${d.id}">
                <button type="submit" class="button">提交</button>
            </form>
        </div>
    </div>

    <%-- 日记配图 --%>
    <div class="panel">
        <h3>配图</h3>
        <form id="addImageForm" action="admin_diary_image_add" method="post" enctype="multipart/form-data">
            <input id="image" name="image" accept="image/*" type="file">
            <input type="hidden" name="did" value="${d.id}">
            <button type="submit" class="button">提交</button>
        </form>

        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>配图</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${dis}" var="di">
                <tr>
                    <td>${di.id}</td>
                    <td><img src="img/${u.id}/diary/${di.id}.jpg" style="max-width:100px; max-height: 100px;"></td>
                    <td><a href="admin_diary_image_delete?id=${di.id}" deleteLink="true">删除</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>
<%@include file="../include/admin/adminFooter.jsp"%>

