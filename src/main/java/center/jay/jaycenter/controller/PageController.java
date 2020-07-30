package center.jay.jaycenter.controller;

import center.jay.jaycenter.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 用于页面的简单服务端跳转
 * 一些无需拿数据的页面
 */
@Controller
@RequestMapping("")
public class PageController {

    // 注册页面
    @RequestMapping("register_page")
    public String registerPage() {
        return "fore/registerPage.jsp";
    }

    // 登陆页面
    @RequestMapping("login_page")
    public String loginPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        String message = "未登录";
        if (user != null) {
            message = "当前用户：" + user.getNickname();
        }

        model.addAttribute("message", message);
        return "fore/loginPage.jsp";
    }
}
