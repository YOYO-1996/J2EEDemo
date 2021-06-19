package run.liuliuqiu.j2ee.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import run.liuliuqiu.j2ee.entity.UserInfo;


/**
 * @author ：Tong
 * @date ：Created in 2020/1/28 22:19
 * @description：
 * @version: $
 */
public class UserFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
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

    public void destroy() {

    }
}
