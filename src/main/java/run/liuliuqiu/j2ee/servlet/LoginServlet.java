package run.liuliuqiu.j2ee.servlet;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import run.liuliuqiu.j2ee.entity.ReData;
import run.liuliuqiu.j2ee.entity.UserInfo;
import run.liuliuqiu.j2ee.service.LoginService;


/**
 * @author ：Tong
 * @date ：Created in 2020/1/28 20:09
 * @description：手机号和密码登录
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
                reData = loginOn(req, resp);
                logger.info("=====登录=====END=====");
            } else if (url.equals("queryUserInfo")) {
                logger.info("=====查询用户信息=====BEGIN=====");
                reData = queryUserInfo(req, resp);
                logger.info("=====查询用户信息=====END=====");
            } else if (url.equals("loginFail")) {

            }
            String responseData = new ObjectMapper().writeValueAsString(reData);
            logger.info("=====ReData数据:" + responseData);
            resp.getWriter().print(responseData);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info(e.getMessage() + e.getStackTrace());
        }
    }

    /**
     * @Description: 登录流程
     * @Param: [request]
     * @Return: entity.ReData
     * @Date: 2020/2/14
     **/
    public ReData loginOn(HttpServletRequest request, HttpServletResponse response) {
        String mobile = request.getParameter("mobile");
        String Pwd = request.getParameter("Pwd");
        //        try {
        //            loginService.loginOn(mobile, Pwd);
        //        } catch (Exception e) {
        //
        //        }
        //登录成功
        //获取用户信息
        UserInfo userInfo = loginService.queryUserInfo(mobile);
        logger.info(userInfo);
        HttpSession session = request.getSession();
        session.setAttribute("userInfo", userInfo);
        //不使用tomcat的sessionid,关闭浏览器即失效
        //手动保存
        //        String userInfoStr = JSON.toJSONString(userInfo);
        //        //保存信息在cookie中
        //        Cookie cookie = new Cookie("userInfo", userInfoStr);
        //        cookie.setMaxAge(60);
        //        cookie.setPath("/");
        //        response.addCookie(cookie);
        return ReData.success().addInfo("userInfo", userInfo);
    }

    public ReData queryUserInfo(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        logger.info(userInfo);
        if (userInfo != null) {
            return ReData.success().addInfo("userInfo", userInfo);
        } else {
            return new ReData(1, "您还未登录，请先登录!");
        }
    }
}
