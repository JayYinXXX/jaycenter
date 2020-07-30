<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
        isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%--
    参数：
        u           作者信息
        cs          分类信息
        pattern  浏览模式/编辑模式
--%>

<script>
    // 用户登录状态管理
    let loginStatus = {
        isLogined : false,
        id : "-1",
        name : "未登录",
        nickname : "未登录"
    };

    // 更新登录状态
    function updateStatus() {
        // 同步请求，ajax回调函数会更新全局变量
        $.ajax({
            url : "check_login",
            type : "get",
            async : false,
            dataType : "text",
            success:function (result) {
                if (result !== "no") {
                    // 解析JSON字符串
                    let user = JSON.parse(result);
                    loginStatus.isLogined = true;
                    loginStatus.id = user.id;
                    loginStatus.nickname = user.nickname;
                    loginStatus.name = user.name;
                }
                else {
                    loginStatus.isLogined = false;
                    loginStatus.id = "-1";
                    loginStatus.name = "未登录";
                    loginStatus.nickname = "未登录";
                }
            }
        });
    }

    $(function () {
        // 更新登陆用户信息
        updateStatus();
        $("#loginedUser").html(loginStatus.nickname);

        // 登陆监听
        $(".checkLogin").click(function () {
            updateStatus();
            if (loginStatus.isLogined === false) {
                $("#loginModalButton").click();
            }
            else {
                let urlView = "fore_user_home_view?uid=" + loginStatus.id;
                let urlEdit = "fore_user_home_edit?uid=" + loginStatus.id;
                $("#myView").attr("href", urlView);
                $("#myEdit").attr("href", urlEdit);
            }
            return loginStatus.isLogined;
        });
    });


    // 载入动画
    // Loading阶段检测
    document.onreadystatechange = function () {
        // loading -> interactive -> complete
        if (document.readyState === "interactive"){
            loadingFade();
        }
    };
    // 方法
    function loadingFade() {
        // loading模块默认ID
        let loadingId = "loading";
        // loading画面淡出
        $("#" + loadingId).fadeOut();
        // 动画效果结束删除遮罩
        setTimeout(function () {
            $("#" + loadingId).remove();
        }, 5000);
    }
</script>

<style>
    /*
    loading动画
        loading容器元素 id="loading"
    */
    #loading {
        position: fixed;
        z-index: 2000;
        left: 0;
        top: 0;
        background-color: #fff;
        width: 100vw;
        height: 100vh;
        text-align:center;
    }
    /*动画效果*/
    /* 旋转id为"loading-img"的image */
    #loading-img {
        width: 5rem;
        height: 5rem;
        margin-top: 40vh;
        display: inline-block;
        /*animation (动画) :绑定选择器, 4s完成动画 linear(匀速) infinite(循环) */
        animation: loading 5s linear infinite;
    }

    /*通过@keyframes规则,能够创建动画 , que 定义动画的名称 可自己定义*/
    @keyframes loading {
        /*以百分比来规定改变发生的时间 也可以通过"from"和"to",等价于0% 和 100%*/
        0% {
            /*rotate(2D旋转) scale(放大或者缩小) translate(移动) skew(翻转)*/
            transform: rotate(0deg);
        }
        100% {
            transform: rotate(360deg);
        }
    }

    /* 用户信息模态框 */
    #userModal {
        top: 0;
        left: 0;
    }
    <%-- 登陆模态框 --%>
    #loginModal {
        left:0;
        right:0;
        top:0;
        bottom:0;
        margin:auto;
        width:40vw;
        height:50vh;
    }

    <%-- 分类侧边栏 --%>
    #sideNav {
        top: 0;
        left: 0;
        overflow: auto;
        width: 20vw;
    }
    #sideNav .accordion-navigation a{
        padding-top: 0.5rem;
        padding-bottom: 0.5rem;
    }

</style>


<%--载入画面--%>
<div id="loading">
    <img id="loading-img" src="system_img/loading.png">
</div>

<%--导航按钮--%>
<div style="position: fixed; left: 0; top: 0; z-index: 901; width: 2.5rem; max-height: 10rem">
    <%-- 竖直分割线 --%>
    <%-- 作者信息模态框开关按钮 --%>
    <a id="userModalButton" class="jay-modal-open" jay-modal-id="userModal" style=""><img src="system_img/003.png" alt="123"></a>
    <%-- 分类侧边栏开关 --%>
    <a id="sideNavBtn" class="jay-modal-open" jay-modal-id="sideNav"><img src="system_img/001.png" alt="123"></a>
</div>


<%--分类侧边栏--%>
<div id="sideNav" class="jay-modal">
    <ul class="accordion" data-accordion>
        <li class="accordion-navigation">
            <c:forEach items="${cs}" var="c">
                <a href="#sideId${c.id}">${c.name}</a>
                <div id="sideId${c.id}" class="content" style="padding-top: 0; padding-bottom: 0;">
                    <c:forEach items="${c.articles}" var="article">
                        <a href="fore_user_article_${pattern}?aid=${article.id}" style="display: block;"><i class="fi-page"> ${article.name}</i></a>
                    </c:forEach>
                </div>
            </c:forEach>
        </li>
    </ul>
</div>


<%-- 作者信息模态框内容 --%>
<div id="userModal" class="jay-modal" data-reveal>
    <h4>当前主页：${u.nickname}</h4>
    <a href="fore_user_home_${pattern}?uid=${u.id}">主页</a>
    <a href="admin_user_list">后台主页</a>

    <div style="margin: 1rem 0 1rem 0;">
        <div class="jay-line-row1"></div>
        <b>殷健</b> 的个人主页，持续更新，进一步完善中...
        邮箱：yinjian0316@163.com
        <div class="jay-line-row1"></div>
    </div>

    <%-- 我的用户信息 --%>
    <div>
        <span id="loginedUser">未知</span>
        <a id="loginModalButton" class="jay-modal-open" jay-modal-id="loginModal">登陆</a>
        <a href="logout">退出登陆</a>

        <a id="myView" class="checkLogin" href="#">浏览模式</a>
        <a id="myEdit" class="checkLogin" href="#">编辑模式</a>
    </div>
</div>

<%-- 登陆模态框 --%>
<div id="loginModal" class="jay-modal">
    <div style="margin-top: 2rem;">
        <form id="loginForm" action="login" method="post">
            <input id="name" name="name" placeholder="账户名">
            <input id="password" name="password" placeholder="账户密码">
            <button id="loginSubmit" class="button" type="submit">登陆</button>
        </form>
    </div>
</div>

