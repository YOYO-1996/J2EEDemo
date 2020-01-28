package filter;


import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
        Cookie[] cookies = hsr.getCookies();

        for (Cookie cookie :
                cookies) {

        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
