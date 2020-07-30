<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
        isELIgnored="false" import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="../include/fore/foreHeader.jsp"%>

<style>
    <%-- 主页顶部区块 --%>
    #homeTop{
        position: relative;
    <%--background-image: url("img/${u.id}/home.png");--%>
        background-position: center;
        background-size: cover;
        background-color: #00aaaa;
        height: 10rem;
    }
    #articleTitle {
        position: absolute;
        width: 100%;
        left: 0;
        bottom: 0;
        text-align: center;
        margin-bottom: 2rem;
    }
</style>

<script>
    $(function () {
        // 文章编辑
        let aid = ${a.id};
        // 编辑标题、内容
        $("#articleSubmit").click(function () {
            updateStatus();
            if (loginStatus.isLogined === false) {
                location.href = "login_page";
            }
            else {
                let name = $("#articleName").val();
                let contentMd = testEditor.getMarkdown();
                $.post(
                    "login_article_edit",
                    {"aid" : aid, "name" : name, "contentMd" : contentMd},
                    function (result) {
                        if (result === "success") {
                            let time = new Date();
                            $("#submitTime").html(time.toTimeString());
                        }
                    }
                );
            }
        });
        // 切换前提交更改
        $("#shiftToView").click(function () {
            $("#articleSubmit").click();
        });
        // 5分钟提交一次
        setInterval(function(){
            $("#articleSubmit").click();
        }, 1000*60*5);

        // 置顶开关
        let top = ${a.top ? "true" : "false"};
        let topSwitch = $("#topSwitch");
        if (top) {
            topSwitch.prop("checked", "checked");
        }
        topSwitch.click(function() {
            top = $(this).prop("checked") ? 1 : 0;
            $.get(
                "login_article_edit",
                {aid : aid, top : top}
            );
        });

        // 私密
        let type = "${a.type}";
        let privateSwitch = $("#privateSwitch");
        if (type === "private") {
            privateSwitch.prop("checked", "checked");
        }
        privateSwitch.click(function () {
            type = ($(this).prop("checked")) ? "private" : "public";
            $.get(
                "login_article_edit",
                {aid : aid, type : type}
            );
        });
    })
</script>

<title>用户主页</title>
<div class="workingArea">

    <%-- 模态框组件 --%>
    <%-- 设置浏览模式 --%>
    <c:set var="pattern" value="edit"/>
    <%@include file="../include/fore/modal.jsp"%>

    <%--编辑浏览切换--%>
    <div style="position: fixed; left: 0; top: 6rem; z-index: 801;">
        <a id="shiftToView" href="fore_user_article_view?aid=${a.id}"><i class="fi-page">浏览</i></a>
    </div>

    <%-- 主页顶部区块 --%>
    <div id="homeTop">
        <div id="articleTitle">
            <span class="jay-white-title">${a.name}</span>
        </div>
    </div>

    <%-- 文章浏览/编辑区 --%>
    <div class="panel">
        <div id="articleHead">
            <input id="articleName" value="${a.name}">
            <div class="switch" style="margin: 0;">
                <span id="topSwitchName">置顶:</span>
                <input id="topSwitch" type="checkbox">
                <label for="topSwitch"></label>

                <span id="privateSwitchName">私密:</span>
                <input id="privateSwitch" type="checkbox">
                <label for="privateSwitch"></label>
            </div>


        </div>

        <%-- markdown 编辑区 --%>
        <div id="articleBody" class="panel">
            <%-- 定义ToC容器（目录容器） --%>
            <div class="panel" id="tocContainer">#custom-toc-container</div>

            <div id="editor">
                <textarea style="display:none;">${ac.content}</textarea>
            </div>
        </div>
        <button id="articleSubmit" type="button" class="button" >Submit</button>
        <div id="submitTime">未提交</div>

        <%--        <script src="${pageContext.request.contextPath}/editormd/examples/js/jquery.min.js"></script>--%>
        <script src="${pageContext.request.contextPath}/editormd/editormd.js"></script>
        <script type="text/javascript">
            let testEditor;
            $(function() {
                <%-- 初始化editor --%>
                testEditor = editormd("editor", {
                    height : "600px",
                    syncScrolling : "single",
                    path : "${pageContext.request.contextPath}/editormd/lib/",
                    dialogLockScreen : false,
                    autoFocus : false,
                    tocContainer : "#tocContainer"
                });
            });
        </script>

    </div>


</div>
<%@include file="../include/fore/foreFooter.jsp"%>