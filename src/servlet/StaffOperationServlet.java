package servlet;

import com.alibaba.fastjson.JSONObject;
import entity.Rarity;
import entity.ReData;
import entity.Staff;
import org.apache.log4j.Logger;
import service.StaffOperationService;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
        HttpServletRequest hsr = (HttpServletRequest) req;
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
        try {
            //处理请求
            if (url.equals("addStaffInfo")) {
                logger.info("=====新增干员数据=====BEGIN=====");
                reData = addStaffInfo(req);
                logger.info("=====新增干员数据=====END=====");
            } else if (url.equals("removeStaffInfo")) {
                logger.info("=====删除干员数据=====BEGIN=====");
                reData = removeStaffInfo(req);
                logger.info("=====删除干员数据=====END=====");
            } else if (url.equals("updateStaffInfo")) {
                logger.info("=====修改干员数据=====BEGIN=====");
                reData = updateStaffInfo(req);
                logger.info("=====修改干员数据=====END=====");
            } else if (url.equals("queryStaffInfo")) {
                logger.info("=====查询单条干员数据=====BEGIN=====");
                reData = queryStaffInfo(req);
                logger.info("=====查询单条干员数据=====END=====");
            } else if (url.equals("queryStaffList")) {
                logger.info("=====查询所有干员数据（分页）=====BEGIN=====");
                reData = queryStaffList(req);
                logger.info("=====查询所有干员数据（分页）=====END=====");
            } else if(url.equals("queryStaffListByCon")){
                logger.info("=====根据条件分页查询干员列表=====BEGIN=====");
                reData = queryStaffListByCon(req);
                logger.info("=====根据条件分页查询干员列表=====END=====");
            }
            String responseData = JSONObject.toJSONString(reData);
            logger.info("=====ReData数据:" + responseData);
            res.getWriter().print(responseData);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info(e.getStackTrace());
        }
    }

    /**
     * 新增单条数据
     * @param req
     * @return ReData
     */
    private ReData addStaffInfo(ServletRequest req) {
        String staffInfo = req.getParameter("staff");
        List<Staff> staffList = JSONObject.parseArray(staffInfo, Staff.class);
        sos.addStaffInfo(staffList.get(0));
        return ReData.success();
    }

    /**
     * 删除单条数据
     * @param req
     * @return ReData
     */
    private ReData removeStaffInfo(ServletRequest req) {
        int staId = Integer.parseInt(req.getParameter("staId"));
        sos.removeStaffInfo(staId);
        return ReData.success();
    }

    /**
     * 更新单条数据
     * @param req
     * @return ReData
     */
    private ReData updateStaffInfo(ServletRequest req) {
        String staffInfo = req.getParameter("staff");
        logger.info("服务端接收到的数据是：" + staffInfo);
        return ReData.success();
    }

    /**
     * 查询单条数据
     * @param req
     * @return ReData
     */
    private ReData queryStaffInfo(ServletRequest req) {

        int id = Integer.parseInt(req.getParameter("id"));
        ArrayList<Staff> list = sos.queryStaffInfo(id);
        return ReData.success().addInfo("staffList", list);
    }

    /**
     * 分页查询数据
     * @param req
     * @return ReData
     */
    private ReData queryStaffList(ServletRequest req) {

        int startIndex = Integer.parseInt(req.getParameter("page"));
        int queryCount = Integer.parseInt(req.getParameter("limit"));
        ArrayList<Staff> staff = sos.queryStaffList((startIndex - 1) * queryCount, queryCount);
        logger.info(staff);
        return ReData.success().addInfo("total", sos.staffCount()).addInfo("staffList", staff);
    }

    private ReData queryStaffListByCon(ServletRequest req) {

        int startIndex = Integer.parseInt(req.getParameter("page"));
        int queryCount = Integer.parseInt(req.getParameter("limit"));
        String name = req.getParameter("name");
        String career = req.getParameter("career");
        String faction = req.getParameter("faction");
        String rarityString = req.getParameter("rarityList");

        logger.info("干员姓名："+name+"\n"+"职业"+career+"\n"+"派别"+faction);
        List<Rarity> rarityList = JSONObject.parseArray(rarityString, Rarity.class);
        logger.info("星级"+rarityList);
        ArrayList<Staff> staff = sos.queryStaffListByCon((startIndex - 1) * queryCount, queryCount, name, career, faction, rarityList);
        logger.info(staff);
        return ReData.success().addInfo("total", sos.staffCount()).addInfo("staffList", staff);
    }
}
