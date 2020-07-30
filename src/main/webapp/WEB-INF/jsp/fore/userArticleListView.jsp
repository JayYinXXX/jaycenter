<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
        isELIgnored="false" import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="../include/fore/foreHeader.jsp"%>
<%@include file="../include/fore/foreNavigator.jsp"%>

<%--jsp参数
        u - 用户User
        as - 文章List
        pattern - 浏览模式
--%>

<style>
    /* 主页顶部区块 */
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
        margin-bottom: 1rem;
    }

    /* 麦哲伦导航 */
    #middle-nav {
        font-size: 150%;
        padding: 20px 0 10px 0;
    }
    .sub-nav dd {
        font-size: 130%;
    }
    [data-magellan-expedition], [data-magellan-expedition-clone] {
        padding: 1px;
    }

    /* 中部区域 */
    #homeMiddle {
        max-width: 62.5rem;
        margin-left: auto;
        margin-right: auto;
    }
    /* 中部内容面板 */
    .middle-div {
        margin-bottom: 30px;
    }

    /* 标签 */
    .article-tag {
        margin: 0;
        padding: 0.3rem 1rem 0.3rem 1rem;
    }

</style>


<title>${u.nickname}笔记列表</title>
<div class="workingArea">

    <%-- 模态框组件 --%>
    <%-- 设置浏览模式 --%>
    <c:set var="pattern" value="view"/>
    <%@include file="../include/fore/modal.jsp"%>

    <%-- 主页顶部区块 --%>
    <div id="homeTop">
        <div id="articleTitle">
            <span class="jay-white-title">文章列表</span>
        </div>
    </div>

    <%-- 麦哲伦导航 --%>
    <div id="middle-nav">
        <div data-magellan-expedition="fixed">
            <dl class="sub-nav">
                <dd data-magellan-arrival="page1" style="margin-left: 10rem;"><a href="#page1">笔记</a></dd>
            </dl>
        </div>
        <hr>
    </div>

    <%-- 功能模块 --%>
    <div id="homeMiddle">

        <%-- 文章列表 --%>
        <div class="middle-div">
            <a name="page1"></a>
            <h3 data-magellan-destination="page1">笔记</h3>
            <a href="fore_user_article_list_view?flag=user&uid=${u.id}">全部笔记</a>
            <div class="jay-line-row1"></div>

            <c:forEach items="${as}" var="article">
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

    </div>


</div>
<%@include file="../include/fore/foreFooter.jsp"%>