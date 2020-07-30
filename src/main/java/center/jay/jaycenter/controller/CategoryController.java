package center.jay.jaycenter.controller;

import center.jay.jaycenter.pojo.Article;
import center.jay.jaycenter.pojo.Category;
import center.jay.jaycenter.pojo.User;
import center.jay.jaycenter.service.ArticleService;
import center.jay.jaycenter.service.CategoryService;
import center.jay.jaycenter.service.UserService;
import center.jay.jaycenter.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;

    @RequestMapping("admin_category_add")
    public String add(Category c) throws IOException {
        // 默认序号为0
        c.setRank(0);
        categoryService.add(c);

        return "redirect:admin_category_list?uid=" + c.getUid();
    }

    @RequestMapping("admin_category_delete")
    public String delete(int id) {
        Category c = categoryService.get(id);

        // 只有as为空删除才能生效
        List<Article> as = articleService.listByCid(id);
        if (as.isEmpty()) {
            categoryService.delete(id);
        }

        return "redirect:admin_category_list?uid=" + c.getUid();
    }

    // 进入编辑页面
    @RequestMapping("admin_category_edit")
    public String edit(int id, Model model) {
        Category c = categoryService.get(id);

        model.addAttribute("c", c);
        return "admin/editCategory.jsp";
    }
    // 更新编辑之后的改动
    @RequestMapping("admin_category_update")
    public String update(Category c) throws IOException {
        categoryService.update(c);

        return "redirect:admin_category_list?uid=" + c.getUid();
    }

    @RequestMapping("admin_category_list")
    public String list(int uid, Model model, Page page) {
        User u = userService.get(uid);

        // 分页，设置起始位置和偏移
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Category> cs = categoryService.listByUid(uid);
        // 分页，获取总页数
        int total = (int) new PageInfo<>(cs).getTotal();
        page.setTotal(total);
        page.setParam("&uid=" + uid);

        model.addAttribute("u", u);
        model.addAttribute("cs", cs);
        model.addAttribute("page", page);
        return "admin/listCategory.jsp";
    }
}
