package filter;


import entity.UserInfo;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author ：Tong
 * @date ：Created in 2020/1/28 22:19
 * @description：
 * @version: $
 */
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hsr = (HttpServletRequest) request;
        //tomcat 会在cookie中存jsessionid
        //直接获取session
        HttpSession session = hsr.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo != null) {

        } else {

        }


        chain.doFilter(hsr, response);
    }

    @Override
    public void destroy() {

    }
}
