package center.jay.jaycenter.interceptor;

import center.jay.jaycenter.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * 登录拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 无需登录授权的url白名单
        String[] noNeedAuthPage = { "fore_user_home_view",
                                    "fore_user_article_view",
                                    "fore_user_article_list_view"};
        HttpSession session = request.getSession();
        String contextPath = session.getServletContext().getContextPath();
        String uri = request.getRequestURI();
        uri = StringUtils.remove(uri, contextPath);
        // fore和login前缀
        if (uri.startsWith("/fore") || uri.startsWith("/login")) {
            // 截取url
            String method = StringUtils.substringAfterLast(uri, "/");
            // 如果授权列表不包含该url，或者以login为前缀的url，必须登录
            if (!Arrays.asList(noNeedAuthPage).contains(method) || uri.startsWith("/login")) {
                User user = (User) session.getAttribute("user");
                if (user == null) {
                    response.sendRedirect("login_page");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
