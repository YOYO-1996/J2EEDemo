package servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import entity.ReData;
import entity.UserInfo;
import org.apache.log4j.Logger;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：Tong
 * @date ：Created in 2020/1/28 20:09
 * @description：
 * @version: $
 */
public class LoginServlet extends HttpServlet {
    Logger logger = Logger.getLogger(LoginServlet.class);
    LoginService loginService = new LoginService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        HttpServletRequest hsr = req;
        String url = hsr.getPathInfo().substring(1);

        logger.info(hsr.getContextPath()
                + "\n" + hsr.getPathInfo()
                + "\n" + hsr.getMethod()
                + "\n" + hsr.getQueryString()
                + "\n" + hsr.getServletPath()
                + "\n" + hsr.getRemoteAddr()
                + "\n" + hsr.getRemoteUser()
                + "\n" + hsr.getPathTranslated()
                + "\n" + hsr.getRequestURI());
        ReData reData = new ReData();
        try {
            //处理请求
            if (url.equals("loginOn")) {
                logger.info("=====登录====BEGIN=====");
                reData = loginOn(req);
                logger.info("=====登录=====END=====");
            } else if (url.equals("queryUserInfo")) {
                logger.info("=====查询用户信息=====BEGIN=====");
                reData = queryUserInfo(req,resp);
                logger.info("=====查询用户信息=====END=====");
            }
            String responseData = JSONObject.toJSONString(reData);
            logger.info("=====ReData数据:" + responseData);
            resp.getWriter().print(responseData);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info(e.getMessage() + e.getStackTrace());
        }
    }

    public ReData loginOn(HttpServletRequest request) {

        return ReData.success();
    }

    public ReData queryUserInfo(HttpServletRequest request,HttpServletResponse response) {
        int userMobile = Integer.parseInt(request.getParameter("userMobile"));
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getDomain().equals("/login")) {

                }
            }
        } else {//无cookie或失效
            //获取用户信息
            UserInfo userInfo = loginService.queryUserInfo(userMobile);
            String userInfoStr = JSON.toJSONString(userInfo);
            //保存信息在cookie中
            Cookie cookie = new Cookie("userInfo", userInfoStr);
            cookie.setMaxAge(60);
            cookie.setPath("/queryUserInfo/");
            response.addCookie(cookie);
        }
        return ReData.success();
    }
}
