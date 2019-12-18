package utils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @description:
 * @author: Tong
 * @date: 2019-12-16 21:13
 */
public class EncodingFilter implements Filter {

    private String encoding; // 定义变量接收初始化的值

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // 设置字符编码链锁
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        chain.doFilter(request, response);

    }

    @Override
    // 初始化
    public void init(FilterConfig config) throws ServletException {
        // 接收web.xml配置文件中的初始参数
        encoding = config.getInitParameter("CharsetEncoding");

    }

}