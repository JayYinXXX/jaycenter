package center.jay.jaycenter.controller;

import center.jay.jaycenter.pojo.*;
import center.jay.jaycenter.service.*;

import com.alibaba.druid.filter.AutoLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 前台控制
 * 1.用户主页，浏览模式（无需登陆）
 *      文章浏览
 * 2.用户主页，编辑模式（登陆）
 *      文章编辑
 */
@Controller
@RequestMapping("")
public class ForeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleContentService articleContentService;
    @Autowired
    UserService userService;
    @Autowired
    DiaryService diaryService;

    // 浏览模式
    // 用户主页
    @RequestMapping("fore_user_home_view")
    public String userHomeView(int uid, Model model, HttpSession session) {
        User u = userService.get(uid);
        // 分类
        List<Category> cs = categoryService.listByUid(uid);
        categoryService.fillArticles(cs);
        // 置顶+最新文章
        List<Article> latestAs = articleService.latestArticles(uid, 10);
        articleService.fillTags(latestAs); // 填充tags
        // 最新日记（数量为1）
        List<Diary> ds = diaryService.listRecentByUid(uid, 1);
        Diary d = new Diary();
        DiaryImage di = null;
        if (!ds.isEmpty()) {
            d = ds.get(0);
            // 最新日记配图
            diaryService.fillImages(d);
            if (!d.getDis().isEmpty()) {
                di = d.getDis().get(0);
            }
        }
        // 两周日记
        List<Diary> twoWeeks = diaryService.twoWeeks(uid);

        model.addAttribute("u", u);
        model.addAttribute("cs", cs);
        model.addAttribute("latestAs", latestAs);
        model.addAttribute("d", d);
        model.addAttribute("di", di);
        model.addAttribute("twoWeeks", twoWeeks);
        return "fore/userHomeView.jsp";
    }

    // 文章列表
    @RequestMapping("fore_user_article_list_view")
    public String userArticleList(String flag, Integer uid, Integer tid, Model model) {
        User u = userService.get(uid);
        // 分类
        List<Category> cs = categoryService.listByUid(uid);
        categoryService.fillArticles(cs);

        // 文章列表
        List<Article> as = null;
        // "tag"
        if ("tag".equals(flag)) {
            if (tid != null) {
                as = articleService.listByTid(tid);
                articleService.fillTags(as);
            }
        }
        // "user"
        else if ("user".equals(flag)) {
            if (uid != null) {
                as = articleService.listByUid(uid);
                articleService.fillTags(as);
            }
        }

        model.addAttribute("u", u);
        model.addAttribute("cs", cs);
        model.addAttribute("as", as);
        return "fore/userArticleListView.jsp";
    }

    // 文章内容浏览页面
    @RequestMapping("fore_user_article_view")
    public String userArticleView(int aid, Model model) {
        Article a = articleService.get(aid);
        ArticleContent ac = articleContentService.getByAid(aid);
        User u = userService.get(a.getUid());
        List<Category> cs = categoryService.listByUid(a.getUid());
        for (Category c : cs) {
            categoryService.fillArticles(c);
        }

        model.addAttribute("u", u);
        model.addAttribute("cs", cs);
        model.addAttribute("a", a);
        model.addAttribute("ac", ac);
        return "fore/userArticleView.jsp";
    }


    // 编辑模式
    // 用户主页
    @RequestMapping("fore_user_home_edit")
    public String userHomeEdit(int uid, HttpSession session, Model model) {
        // 检查登录信息
        User user = (User) session.getAttribute("user");
        if (user == null || user.getId() != uid) {
            return "redirect:login_page";
        }

        User u = userService.get(uid);
        List<Category> cs = categoryService.listByUid(uid);
        for (Category c : cs) {
            categoryService.fillArticles(c);
        }

        model.addAttribute("u", u);
        model.addAttribute("cs", cs);
        return "fore/userHomeEdit.jsp";
    }

    // 文章编辑页面
    @RequestMapping("fore_user_article_edit")
    public String userArticleEdit(int aid, HttpSession session, Model model) {
        Article a = articleService.get(aid);

        // 检查登录信息
        User user = (User) session.getAttribute("user");
        if (user == null || user.getId() != a.getUid()) {
            return "redirect:login_page";
        }

        ArticleContent ac = articleContentService.getByAid(aid);
        User u = userService.get(a.getUid());
        List<Category> cs = categoryService.listByUid(a.getUid());
        for (Category c : cs) {
            categoryService.fillArticles(c);
        }

        model.addAttribute("u", u);
        model.addAttribute("cs", cs);
        model.addAttribute("a", a);
        model.addAttribute("ac", ac);
        return "fore/userArticleEdit.jsp";
    }


    // 其他页面

    // 账号注册
    @RequestMapping("register")
    public String register(User u, Model model) {
        // 避免输入空值
        if (u.getName()==null || u.getPassword() == null || u.getNickname()==null) {
            String message = "注册失败";
            model.addAttribute("message", message);
            return "fore/messagePage.jsp";
        }
        // html转义，检测用户名是否已经存在
        String name = u.getName();
        name = HtmlUtils.htmlEscape(name);
        if (userService.isExist(name)) {
            String message = "该用户名已经被使用";
            model.addAttribute("message", message);
            return "fore/messagePage.jsp";
        }

        u.setName(name);
        u.setCreateDate(new Date());
        userService.add(u);

        String message = "注册成功";
        model.addAttribute("message", message);
        return "fore/messagePage.jsp";
    }

    // 登陆
    @RequestMapping("login")
    public String login(String name, String password, String url, HttpSession session, Model model) {
        name = HtmlUtils.htmlEscape(name);
        User u = userService.getByNameAndPassword(name, password);
        // 判断账号密码是否正确，是否能取出对象
        if (u == null) {
            String message = "登陆失败";
            model.addAttribute("message", message);
            return "fore/messagePage.jsp";
        }
        // 登陆成功，在session中写入参数user
        session.setAttribute("user", u);

        return "redirect:fore_user_home_edit?uid=" + u.getId();
    }

    // 退出登陆，返回用户主页
    @RequestMapping("logout")
    public String logout(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        // 没有已登陆用户，session中没有找到user，跳转到jay的主页
        if (user == null) {
            String message = "没有找到已登陆账号";
            model.addAttribute("message", message);
            return "fore/messagePage.jsp";
        }
        // 成功退出，返回退出用户的主页（浏览模式）
        int uid = user.getId();
        session.removeAttribute("user");
        return "redirect:fore_user_home_view?uid=" + uid;
    }

    // AJAX 检测是否登陆
    @RequestMapping("check_login")
    @ResponseBody
    public String checkLogin(HttpSession session) {
        User u = (User) session.getAttribute("user");
        // 未登陆
        if (u == null) {
            return "no";
        }
        // 已登陆，返回nickname
        String s1 = "\"id\":" + "\"" + u.getId() + "\"";
        String s2 = "\"name\":" + "\"" + u.getName() + "\"";
        String s3 = "\"nickname\":" + "\"" + u.getNickname() + "\"";
        String result = "{" + s1 + "," + s2 + "," + s3 + "}";

        return result;
    }
}
