<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
        isELIgnored="false" import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="../include/fore/foreHeader.jsp"%>

<%--
    u - User
    a - Article
    ac - ArticleContent
--%>

<script>
    $(function(){
        // scroll滚动目录的初始位置，在对应页面去获取
        let top0 = $(".scroll").offset().top;
        let left0 = $(".scroll").offset().left;
        let width0 = $(".scroll").outerWidth();
        let height0 = $(".scroll").outerHeight();
        // 滚动条监听
        $(window).scroll(function() {
            // 滚动条位移
            let win_top = $(this).scrollTop();
            // 父容器，容纳高度
            let containerHight = $(".scroll").parent().height();
            // 上
            if (win_top < top0) {
                $(".scroll").css({"position": "absolute", "left": 0, "top": 0, "width": width0, "height": height0});
            }
            // 中
            if (win_top >= top0 && win_top < top0 + containerHight - height0) {
                $(".scroll").css({"position": "fixed", "left": left0, "top": 0, "width": width0, "height": height0});
            }
            // 下
            if (win_top >= top0 + containerHight - height0) {
                $(".scroll").css({
                    "position": "absolute",
                    "left": 0,
                    "top": containerHight - height0,
                    "width": width0,
                    "height": height0
                });
            }
        })
    })
</script>

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
    <%-- 文章体/目录/正文 --%>
    #articleBody {
        max-width:100%;
        min-height: 100vh;
    }
    #catalog {
        border: solid 1px #666666;
        box-shadow: 0 0 5px rgba(0, 0, 0, 0.4);
        padding: 5px 5px 5px 5px;
    }
    #content {
        min-height: 100vh;
        background-color: rgb(255, 255, 255);
        border: solid 1px #666666;
        box-shadow: 0 0 5px rgba(0, 0, 0, 0.4);
        padding: 5px 5px 5px 5px;
    }
</style>


<title>${u.nickname}的主页</title>
<div class="workingArea">

    <%-- 模态框组件 --%>
    <%-- 设置浏览模式 --%>
    <c:set var="pattern" value="view"/>
    <%@include file="../include/fore/modal.jsp"%>

    <%--编辑浏览切换--%>
    <div style="position: fixed; left: 0; top: 6rem; z-index: 801;">
        <a href="fore_user_article_edit?aid=${a.id}"><i class="fi-page-edit">编辑</i></a>
    </div>

    <%-- 主页顶部区块 --%>
    <div id="homeTop">
        <div id="articleTitle">
            <span class="jay-white-title">${a.name}</span>
        </div>
    </div>

    <%-- 文章浏览/编辑区 --%>
    <div class="panel">
        <%-- 文章头 --%>
        <div id="articleHead">
            <input id="articleName" value="${a.name}">
        </div>

        <%-- 文章体 --%>
        <div id="articleBody" class="row panel" data-equalizer>
            <div class="large-3 columns" style="position: relative;" data-equalizer-watch>
                <div id="catalog" class="scroll" >
                    <div style="overflow: auto; height: 98%;">
                        <%-- 定义ToC容器（目录容器） --%>
                        <div id="tocContainer">#custom-toc-container</div>
                    </div>
                </div>
            </div>
            <div class="large-9 columns" style="padding-right: 0;" data-equalizer-watch>
                <div id="content">
                    <%-- 编辑器 --%>
                    <div id="editor">
                        <textarea style="display:none;">${ac.content}</textarea>
                    </div>
                </div>
            </div>
        </div>
        <div style="background-color: aquamarine;">123456</div>

        <%-- 编辑器js --%>
        <%--        <script src="${pageContext.request.contextPath}/editormd/examples/js/jquery.min.js"></script>--%>
        <script src="${pageContext.request.contextPath}/editormd/editormd.js"></script>
        <script src="${pageContext.request.contextPath}/editormd/lib/marked.min.js"></script>
        <script src="${pageContext.request.contextPath}/editormd/lib/prettify.min.js"></script>
        <script src="${pageContext.request.contextPath}/editormd/lib/raphael.min.js"></script>
        <script src="${pageContext.request.contextPath}/editormd/lib/underscore.min.js"></script>
        <script src="${pageContext.request.contextPath}/editormd/lib/sequence-diagram.min.js"></script>
        <script src="${pageContext.request.contextPath}/editormd/lib/flowchart.min.js"></script>
        <script src="${pageContext.request.contextPath}/editormd/lib/jquery.flowchart.min.js"></script>

        <script type="text/javascript">
            let editorView;
            $(function() {
                <%-- 初始化editor --%>
                editorView = editormd.markdownToHTML("editor", {
                    tocContainer : "#tocContainer"
                });
            });
        </script>

    </div>



</div>
<%@include file="../include/fore/foreFooter.jsp"%>