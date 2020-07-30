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
</script>

<title>用户管理</title>

<div class="workingArea">

    <%-- 用户面板 --%>
    <div class="panel">
        <h3>用户管理</h3>
        <br>
        <div class="listDataTableDiv">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>用户名</th>
                        <th>昵称</th>
                        <th>分类管理</th>
                        <th>文章管理</th>
                        <th>日记管理</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach items="${us}" var="u">
                        <tr>
                            <td>${u.id}</td>
                            <td>${u.name}</td>
                            <td>${u.nickname}</td>
                            <td><a href="admin_category_list?uid=${u.id}">分类管理</a></td>
                            <td><a href="#">文章管理</a></td>
                            <td><a href="admin_diary_list?uid=${u.id}">日记管理</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

<%--        &lt;%&ndash; 分页 &ndash;%&gt;--%>
<%--        <div class="pageDiv">--%>
<%--            <%@include file="../include/admin/adminPage.jsp"%>--%>
<%--        </div>--%>
    </div>


</div>
<%@include file="../include/admin/adminFooter.jsp"%>
