/**
 * 基于jQuery
 */

$(function () {
    /*
        模态框
    */
    // 1. 设置监听的元素
    //      设置类:     class = "jayModalOpen"
    //      设置属性:   jayModalId="test" （test的值与控制的模态框id匹配）
    // 2. 自定义模态框：<div id="test" class="jayModal"><div>
    //      设置类:     class = "jayModal"
    
    $(".jay-modal-open").click(function () {
        let modalId = $(this).attr("jay-modal-id");
        let modalMaskLayerId = modalId + "mask-layer";
        // 新建遮罩层
        let div = $("<div></div>");
        div.attr("id", modalMaskLayerId)
        div.attr("class", "jay-mask-layer")
        $("#" + modalId).before(div);
        // 打开模态框和遮罩层
        $("#" + modalId).fadeIn();
        $("#" + modalMaskLayerId).fadeIn();
        // 点击遮罩触发，关闭模态框和遮罩层
        $("#" + modalMaskLayerId).click(function () {
            $("#" + modalId).fadeOut();
            $("#" + modalMaskLayerId).fadeOut();
            // 动画效果结束删除遮罩
            setTimeout(function () { 
                $("#" + modalMaskLayerId).remove();
            }, 500);
        });
    });


    /*
        条形超链
    */

    
    /*
        下拉菜单
    */
    // 悬停打开
    $(".jayDropdown").mouseenter(function(){
        // 清空控制条
        $(".jayDropdownControl").remove();
        // 清空目标容器
        let jayDropdownContainerId = $(this).attr("jayDropdownContainerId");
        $("#"+jayDropdownContainerId).empty();
        // 将列表拷贝到目标容器
        let listId = $(this).attr("jayDropdownListId");
        let list = $("#"+listId).clone();
        $("#"+jayDropdownContainerId).append(list);
        list.fadeIn();

        // 增加一个悬停控制条
        let controlDiv = $("<div></div>");
        controlDiv.addClass("jayDropdownControl");
        let left = $("#"+jayDropdownContainerId).position().left + $("#"+jayDropdownContainerId).width() + 10;
        controlDiv.css("left", left);
        $("#"+jayDropdownContainerId).after(controlDiv);
        // 离开控制条退出下拉
        $(".jayDropdownControl").mouseenter(function(){
            // 清空下拉容器
            $("#"+jayDropdownContainerId).empty();
            $(this).remove();
        });
    });

    /*
        限制在容器范围内的fixed
    */
});





