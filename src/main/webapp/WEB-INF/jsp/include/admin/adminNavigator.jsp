<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
        isELIgnored="false"%>

<%-- 置顶导航 --%>
<nav class="top-bar" data-topbar>
    <%-- 标题logo区 --%>
    <ul class="title-area">
        <li class="name">
            <h1><a href="fore_user_home_view?uid=1">JayYin的主页</a></h1>
        </li>
        <%-- 小屏幕上的折叠按钮 --%>
        <li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a></li>
    </ul>

    <section class="top-bar-section">
        <ul class="left">
            <li><a href="#">后台主页</a></li>
            <li id="navUser"><a href="admin_user_list">用户管理</a></li>
        </ul>
    </section>
</nav>
