package center.jay.jaycenter.controller;

import center.jay.jaycenter.pojo.*;
import center.jay.jaycenter.service.*;
import center.jay.jaycenter.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ArticleContentService articleContentService;
    @Autowired
    UserService userService;
    @Autowired
    ArticleTagService articleTagService;

    @RequestMapping("admin_category_article_add")
    public String add(Article a) {
        a.setCreateDate(new Date());  // 创建日期
        a.setVersion(1);  // 版本号初始为1
        a.setUpdateUid(a.getUid());  // 更新人id，默认为作者自己
        a.setUpdateDate(new Date());  // 更新日期
        a.setType(Article.PUBLIC);
        a.setRank(0);
        articleService.add(a);

        // 拿到新建article的主键，新建一个对应的article_content
        int aid = a.getId();
        ArticleContent ac = new ArticleContent();
        ac.setAid(aid);
        ac.setContent("空文档");
        articleContentService.add(ac);

        return "redirect:admin_category_article_list?cid=" + a.getCid();
    }

    @RequestMapping("admin_category_article_edit")
    public String edit(int id, Model model) {
        Article a = articleService.get(id);
        ArticleContent ac = articleContentService.getByAid(id);
        List<ArticleTag> ownedTags = articleTagService.listByAid(id);
        List<ArticleTag> remainTags = articleTagService.listRemainByAid(id);
        List<ArticleTag> userTags = articleTagService.listByUid(a.getUid());

        model.addAttribute("a", a);
        model.addAttribute("ac", ac);
        model.addAttribute("ownedTags", ownedTags);
        model.addAttribute("remainTags", remainTags);
        model.addAttribute("userTags", userTags);
        return "admin/editCategoryArticle.jsp";
    }

    @RequestMapping("admin_category_article_update")
    public String update(Article a) {
        // 更新article
        a.setVersion(a.getVersion() + 1);  // 更新版本号
        a.setUpdateUid(a.getUid());  // 更新人id，默认为作者自己
        a.setUpdateDate(new Date());  // 更新日期
        articleService.update(a);

        // 注释掉，此处不允许修改文章内容
        // 更新articleContent
//        ArticleContent ac = a.getArticleContent();
//        int acid = articleContentService.getByAid(a.getId()).getId();  // 获取content的id
//        ac.setId(acid);
//        articleContentService.update(ac);

        return "redirect:admin_category_article_list?cid=" + a.getCid();
    }

    @RequestMapping("admin_category_article_delete")
    public String delete(int id) {
        Article a = articleService.get(id);
        int cid = a.getCid();
        // 先删除content
        ArticleContent ac = articleContentService.getByAid(id);
        articleContentService.delete(ac.getId());
        // 删除article
        articleService.delete(id);

        return "redirect:admin_category_article_list?cid=" + cid;
    }

    // 列出分类所属article
    @RequestMapping("admin_category_article_list")
    public String list(int cid, Page page, Model model) {
        Category c = categoryService.get(cid);
        User u = userService.get(c.getUid());

        // 分页查询 取出as
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Article> as = articleService.listByCid(cid);
        int total = (int) new PageInfo<>(as).getTotal();
        page.setTotal(total);
        page.setParam("&cid=" + cid);

        model.addAttribute("u", u);
        model.addAttribute("c", c);
        model.addAttribute("as", as);
        model.addAttribute("page", page);
        return "admin/listCategoryArticle.jsp";
    }
    // 列出标签所属article


    // AJAX 异步方法
    // 修改分类
    @RequestMapping(value = "login_article_edit", params = {"aid", "cid"})
    @ResponseBody
    public String articleUpdate(int aid, int cid) {
        Article a = articleService.get(aid);
        a.setCid(cid);
        articleService.update(a);

        return "success";
    }
    // 置顶
    @RequestMapping(value = "login_article_edit", params = {"aid", "top"})
    @ResponseBody
    public String articleUpdate(int aid, boolean top) {
        // 更新article
        Article a = articleService.get(aid);
        a.setTop(top);
        articleService.update(a);

        return "success";
    }
    // 公开/私密
    @RequestMapping(value = "login_article_edit", params = {"aid", "type"})
    @ResponseBody
    public String articleUpdate(int aid, String type) {
        // 更新article
        Article a = articleService.get(aid);
        if (type.equals(Article.PUBLIC) || type.equals(Article.PRIVATE)) {
            a.setType(type);
        }
        articleService.update(a);

        return "success";
    }
    // 编辑标题、内容
    @RequestMapping(value = "login_article_edit", params = {"aid", "name", "contentMd"})
    @ResponseBody
    public String articleUpdate(int aid, String name, String contentMd) {
        // 更新article
        Article a = articleService.get(aid);
        a.setName(name);  // 标题
        a.setVersion(a.getVersion() + 1);  // 更新版本号
        a.setUpdateUid(a.getUid());  // 更新人id，默认为作者自己
        a.setUpdateDate(new Date());  // 更新日期
        articleService.update(a);
        // 更新articleContent内容
        ArticleContent ac = articleContentService.getByAid(aid);
        ac.setContent(contentMd);
        articleContentService.update(ac);

        return "success";
    }

}
