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
 * 后台管理拦截
 * 只有管理员账户能访问后台
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 管理员账户
        String[] administrator = { "yinjian0316" };
        HttpSession session = request.getSession();
        String contextPath = session.getServletContext().getContextPath();
        String uri = request.getRequestURI();
        uri = StringUtils.remove(uri, contextPath);
        // 前台
        if (uri.startsWith("/admin")) {
            // 截取url
            String method = StringUtils.substringAfterLast(uri, "/");
            // 如果登录用户是管理员
            User user = (User) session.getAttribute("user");
            if (user != null && Arrays.asList(administrator).contains(user.getName())) {
                return true;
            }
        }
        response.sendRedirect("login_page");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
