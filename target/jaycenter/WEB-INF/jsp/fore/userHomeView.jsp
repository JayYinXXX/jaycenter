<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
        isELIgnored="false" import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="../include/fore/foreHeader.jsp"%>
<%@include file="../include/fore/foreNavigator.jsp"%>

<%--jsp参数
        u - 用户User
        d - 日记Diary
        latestAs - 最近文章List
        twoWeeks - 两周的Diary
        pattern - 浏览模式
--%>

<script>
    $(function () {
        // 3.1
        $("a[flag='1'], a[flag='9']").click(function() {
            let did = $(this).attr("did");
            // did没有有效赋值
            if (did === "") {
                return false;
            }

            let modal = $("#" + $(this).attr("jay-modal-id"));
            $.ajax({
                url : "ajax_diary_content_list",
                data : { "did" : did },
                dataType : "json",
                // contentType : "application/x-www-form-urlencoded; charset=utf-8",
                success : function(data) {
                    // 动态填充Diary模态框内容
                    for (x in data) { // x 为属性名
                        let str = '<a class="jay-anchor">' + data[x].content + '</a>';
                        let element = $(str);
                        modal.append(element);
                    }
                    // 打开模态框
                    $("#diaryModalBtn").click();
                    // 关闭时清空元素
                    let maskLayerId = modal.attr("id") + "mask-layer";
                    let maskLayer = $("#" + maskLayerId);
                    maskLayer.click(function () {
                        modal.empty();
                    });
                },
            });
        });
    })
</script>

<style>
    /* 1. 主页顶部区块 */
    #homeTop{
        position: relative;
        background-image: url("img/${u.id}/home.jpg");
        background-position: center;
        background-size: cover;
        background-color: #eeaaaa;
        height: 100vh;
    }
    #huafeichi {
        position: absolute;
        width: 25rem;
        left: 5rem;
        top: 5rem;
    }

    /* 2. 麦哲伦导航 */
    #middle-nav {
        font-size: 150%;
        padding: 2rem 0 2rem 0;
    }
    .sub-nav dd {
        font-size: 130%;
    }
    [data-magellan-expedition], [data-magellan-expedition-clone] {
        padding: 1px;
    }

    /* 3. 日记 */
    /* 日记模态框 */
    #diaryModal {
        left: 0;
        right: 0;
        top: 0;
        bottom: 0;
        width: 50vw;
        height: 80vh;
        margin: auto;
    }
    /*3.2 活跃记录*/
    .days {
        padding: 10px 0;
        background: rgb(236, 236, 236);
        margin: 0;
    }
    .days li {
        list-style-type: none;
        display: inline-block;
        width: 13%;
        text-align: center;
        margin-bottom: 5px;
        color: #777;
    }
    .days li a[flag] {
        /* padding: 5px; */
        display: inline-block;
        width: 1.5rem;
        height: 1.5rem;
    }
    .days li a[flag = "9"] {
        background: #1abc9c;
        color: white !important
    }
    .days li a[flag = "1"] {
        background-color:lightcoral;
        color: white !important
    }

    /* 4. 中部区域 */
    #homeMiddle {
        max-width: 62.5rem;
        margin-left: auto;
        margin-right: auto;
    }

    /* 中部内容面板 */
    .middle-div {
        margin-top: 2rem;
    }

    /* 标签 */
    .article-tag {
        margin: 0;
        padding: 0.3rem 1rem 0.3rem 1rem;
    }


</style>


<title>${u.nickname}的主页</title>
<div class="workingArea">

    <%-- 模态框组件 --%>
    <%-- 设置浏览模式 --%>
    <c:set var="pattern" value="view"/>
    <%@include file="../include/fore/modal.jsp"%>

    <%-- 1. 主页顶部区块 --%>
    <div id="homeTop">
        <div id="huafeichi" class="jay-white-title">花落城中池，花非池中物。</div>
    </div>

    <%-- 2. 麦哲伦导航 --%>
    <div id="middle-nav">
        <div data-magellan-expedition="fixed">
            <dl class="sub-nav">
                <dd data-magellan-arrival="page1" style="margin-left: 10rem;"><a href="#page1">动态</a></dd>
                <dd data-magellan-arrival="page2"><a href="#page2">笔记</a></dd>
                <%--<dd data-magellan-arrival="page3"><a href="#page3">足迹</a></dd>--%>
            </dl>
        </div>
    </div>

    <%-- 3. 动态、todolist --%>
    <div style="background-color: rgb(236, 236, 236); max-height: 100vh; overflow: hidden;">
        <a name="page1"></a>
        <h3 data-magellan-destination="page1"></h3>
        <div class="row" style="max-width: 62.5rem; padding: 1rem 0 1rem 0;">
            <%-- 3.1 最新动态 --%>
            <div class="large-7 columns">
                <a id="diaryModalBtn" class="jay-modal-open" jay-modal-id="diaryModal"></a>
                <a href="javascript:void(0);" class="th" jay-modal-id="diaryModal" flag="1" did="${d.id}" style="max-width: 70%; max-height: 100%;">
                    <img src="img/${u.id}/diary/${di.id}.jpg" onerror="this.onerror=null; this.src='img/${u.id}/diary_default.png'">
                </a>
                <h4>${d.name}</h4>
                <h4><fmt:formatDate value="${d.createDate}" pattern="yyyy-MM-dd"/></h4>
            </div>

            <%-- 3.2 活跃记录 --%>
            <div class="large-5 columns">
                <ul class="days">
                    <c:forEach items="${twoWeeks}" var="d">
                        <li><a href="javascript:void(0);" jay-modal-id="diaryModal" flag="${d.flag}" did="${d.id}">${d.dayOfWeek}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <%-- 3.3 日记模态框内容 --%>
        <div id="diaryModal" class="jay-modal"></div>
    </div>

    <%-- 4. 功能模块 --%>
    <div id="homeMiddle">

        <%-- 4.1 最近文章 --%>
        <div class="middle-div">
            <a name="page2"></a>
            <h3 data-magellan-destination="page2">最近笔记</h3>
            <a class="jay-modal-open" jay-modal-id="sideNav">全部笔记</a>
            <div class="jay-line-row1"></div>

            <c:forEach items="${latestAs}" var="article">
                <div class="row">
                    <div class="large-7 columns">
                        <a href="fore_user_article_${pattern}?aid=${article.id}" style="font-size:200%;">${article.name}</a>
                    </div>
                    <div class="large-5 columns">
                        <div>
                            <fmt:formatDate value="${article.createDate}" pattern="yyyy-MM-dd"/>
                            <c:if test="${article.top == true}">置顶</c:if>
                        </div>
                        <div>
                            <c:forEach items="${article.tags}" var="tag">
                                <a href="fore_user_article_list_view?flag=tag&uid=${u.id}&tid=${tag.id}" class="button info round article-tag">${tag.name}</a>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="jay-line-row1"></div>
            </c:forEach>

        </div>

        <%-- 4.2 足迹 --%>
        <%--<div class="middle-div" style="background-color: #eeaaaa;">--%>
        <%--    <a name="page3"></a>--%>
        <%--    <h3 data-magellan-destination="page3">足迹</h3>--%>
        <%--    <p>滚动屏幕，查看导航栏上的变化。</p>--%>
        <%--    <p>滚动屏幕，查看导航栏上的变化。</p>--%>
        <%--    <p>滚动屏幕，查看导航栏上的变化。</p>--%>
        <%--    <p>滚动屏幕，查看导航栏上的变化。</p>--%>
        <%--    <p>滚动屏幕，查看导航栏上的变化。</p>--%>
        <%--    <p>滚动屏幕，查看导航栏上的变化。</p>--%>
        <%--    <p>滚动屏幕，查看导航栏上的变化。</p>--%>
        <%--</div>--%>
    </div>


</div>
<%@include file="../include/fore/foreFooter.jsp"%>