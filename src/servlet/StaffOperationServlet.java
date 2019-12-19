package servlet;

import entity.ReData;
import entity.Staff;
import org.apache.log4j.Logger;
import service.StaffOperationService;
import utils.JSONUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Tong
 * @date: 2019-12-16 20:00
 */
public class StaffOperationServlet extends HttpServlet {
    Logger logger = Logger.getLogger(StaffOperationServlet.class);
    StaffOperationService sos = new StaffOperationService();

    @Override
    public void service(ServletRequest req, ServletResponse res) {
        HttpServletRequest hsr = (HttpServletRequest)req;
        String url = hsr.getPathInfo().substring(1);

//        logger.info(hsr.getContextPath()
//                +"\n"+hsr.getPathInfo()
//                +"\n"+hsr.getMethod()
//                +"\n"+hsr.getQueryString()
//                +"\n"+hsr.getServletPath()
//                +"\n"+hsr.getRemoteAddr()
//                +"\n"+hsr.getRemoteUser()
//                +"\n"+hsr.getPathTranslated()
//                +"\n"+hsr.getRequestURI());
        ReData reData = new ReData();
        try{
            //处理请求
            if (url.equals("addStaffInfo")) {
                logger.info("=====新增干员数据=====BEGIN=====");
                addStaffInfo(req, res,reData);
                logger.info("=====新增干员数据=====END=====");
            } else if (url.equals("removeStaffInfo")) {
                logger.info("=====删除干员数据=====BEGIN=====");
                removeStaffInfo(req, res,reData);
                logger.info("=====删除干员数据=====END=====");
            } else if (url.equals("updateStaffInfo")) {
                logger.info("=====修改干员数据=====BEGIN=====");
                updateStaffInfo(req, res,reData);
                logger.info("=====修改干员数据=====END=====");
            } else if (url.equals("queryStaffInfo")) {
                logger.info("=====查询单条干员数据=====BEGIN=====");
                queryStaffInfo(req, res,reData);
                logger.info("=====查询单条干员数据=====END=====");
            } else if (url.equals("queryStaffList")) {
                logger.info("=====查询所有干员数据（分页）=====BEGIN=====");
                queryStaffList(req, res,reData);
                logger.info("=====查询所有干员数据（分页）=====END=====");
            }
            String responseData = JSONUtil.beanToJson(reData);
            logger.info("=====ReData数据:" + responseData);
            res.getWriter().print(responseData);
        }catch (IOException e){
            e.printStackTrace();
            logger.info(e.getStackTrace());
            reData.setErrorcode(0001);
            reData.setErrormsg("系统异常");
        }finally {

        }

    }

    private void addStaffInfo(ServletRequest req, ServletResponse res, ReData reData) {
        try {
            String staffInfo = req.getParameter("staff");
            logger.info("服务端接收到的数据是：" + staffInfo);
            if (JSONUtil.isJsonObject(staffInfo)) {
                Staff staff = (Staff) JSONUtil.stringToBean(staffInfo, Staff.class);
                logger.info("转换为JSON对象之后是：" + staff);
                sos.addStaffInfo(staff);
                reData.setErrorcode(0000);
                reData.setErrormsg("新增干员成功");
            } else {
                logger.error("数据格式有误！");
                reData.setErrorcode(0001);
                reData.setErrormsg("数据格式有误！");
            }
        } catch (ClassNotFoundException | IOException | SQLException e) {
            e.printStackTrace();
            reData.setErrorcode(0001);
            reData.setErrormsg("系统异常！");
        }

    }

    private void removeStaffInfo(ServletRequest req, ServletResponse res, ReData reData) {
        try {
            sos.removeStaffInfo(1);
        } catch (ClassNotFoundException | IOException | SQLException e) {
            e.printStackTrace();
            reData.setErrorcode(0001);
            reData.setErrormsg("系统异常！");
        }
    }

    private void updateStaffInfo(ServletRequest req, ServletResponse res, ReData reData) {
        try {
            String staffInfo = req.getParameter("staff");
            logger.info("服务端接收到的数据是：" + staffInfo);
            if (JSONUtil.isJsonObject(staffInfo)) {
                Staff staff = (Staff) JSONUtil.stringToBean(staffInfo, Staff.class);
                logger.info("转换为JSON对象之后是：" + staff);
                sos.addStaffInfo(staff);
                reData.setErrorcode(0000);
                reData.setErrormsg("新增干员成功");
            } else {
                logger.error("数据格式有误！");
                reData.setErrorcode(0001);
                reData.setErrormsg("数据格式有误！");
            }
        } catch (ClassNotFoundException | IOException | SQLException e) {
            e.printStackTrace();
            reData.setErrorcode(0001);
            reData.setErrormsg("系统异常！");
        }
    }

    private void queryStaffInfo(ServletRequest req, ServletResponse res, ReData reData) {
        try {
            Staff staff = sos.queryStaffInfo(45);
            reData.setErrorcode(0000);
            reData.setErrormsg("查询成功！");
            ArrayList<Staff> list = new ArrayList<>();
            list.add(staff);
            reData.setData(list);
            logger.info(reData);
        } catch (ClassNotFoundException | IOException | SQLException e) {
            e.printStackTrace();
            reData.setErrorcode(0001);
            reData.setErrormsg("系统异常！");
        }

    }

    private void queryStaffList(ServletRequest req, ServletResponse res, ReData reData) {
        try {
            ArrayList<Staff> staff = sos.queryStaffList();
        } catch (ClassNotFoundException | IOException | SQLException e) {
            e.printStackTrace();
            reData.setErrorcode(0001);
            reData.setErrormsg("系统异常！");
        }
    }
}
