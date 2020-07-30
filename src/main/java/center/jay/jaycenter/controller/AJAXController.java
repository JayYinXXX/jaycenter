package center.jay.jaycenter.controller;

import center.jay.jaycenter.pojo.DiaryContent;
import center.jay.jaycenter.service.DiaryContentService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 1. AJAX 异步请求
 * 2. 无需登陆
 * 的方法集中放在这里
 */
@Controller
@RequestMapping("")
public class AJAXController {

    @Autowired
    DiaryContentService diaryContentService;

    // DiaryContent
    // 输出指定Diary的内容列表
    @RequestMapping(value = "ajax_diary_content_list", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String listContent(int did) {
        List<DiaryContent> dcs = diaryContentService.listByDid(did);
        String result = JSON.toJSONString(dcs);

        return result;
    }
}
