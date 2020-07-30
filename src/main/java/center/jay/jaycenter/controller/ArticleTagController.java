package center.jay.jaycenter.controller;

import center.jay.jaycenter.pojo.Article;
import center.jay.jaycenter.pojo.ArticleTag;
import center.jay.jaycenter.pojo.ArticleTagMap;
import center.jay.jaycenter.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ArticleTag + ArticleTagMap 集中到一个控制类中
 */
@Controller
@RequestMapping("")
public class ArticleTagController {
    @Autowired
    ArticleTagService articleTagService;

    // 标签管理
    @RequestMapping("admin_article_tag_add")
    public String add(ArticleTag at, Integer aid) {
        articleTagService.add(at);

        return "redirect:admin_category_article_edit?id=" + aid;
    }

    @RequestMapping("admin_article_tag_delete")
    public String delete(Integer id, Integer aid) {
        articleTagService.delete(id);

        return "redirect:admin_category_article_edit?id=" + aid;
    }

    // [文章-标签]映射关系管理
    @RequestMapping("admin_article_tagmap_add")
    public String addMap(ArticleTagMap atm) {
        articleTagService.addMap(atm);

        return "redirect:admin_category_article_edit?id=" + atm.getAid();
    }

    @RequestMapping("admin_article_tagmap_delete")
    public String deleteMap(ArticleTagMap atm) {
        articleTagService.deleteMap(atm.getAid(), atm.getTid());

        return "redirect:admin_category_article_edit?id=" + atm.getAid();
    }
}
