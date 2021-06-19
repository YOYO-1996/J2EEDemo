package run.liuliuqiu.j2ee.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import run.liuliuqiu.j2ee.entity.Rarity;
import run.liuliuqiu.j2ee.entity.ReData;
import run.liuliuqiu.j2ee.entity.Staff;
import run.liuliuqiu.j2ee.service.StaffOperationService;
import run.liuliuqiu.j2ee.utils.StringUtils;


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
            } else if (url.equals("queryStaffListByCon")) {
                logger.info("=====根据条件分页查询干员列表=====BEGIN=====");
                reData = queryStaffListByCon(req);
                logger.info("=====根据条件分页查询干员列表=====END=====");
            }
            String responseData = new ObjectMapper().writeValueAsString(reData);
            logger.info("=====ReData数据:" + responseData);
            res.getWriter().print(responseData);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info(e.getMessage() + e.getStackTrace());
        }
    }

    /**
     * 新增单条数据
     *
     * @param req
     * @return ReData
     */
    private ReData addStaffInfo(ServletRequest req) throws JsonProcessingException {
        String staffInfo = req.getParameter("staff");
        List<Staff> staffList = new ObjectMapper()
                .readValue(staffInfo, TypeFactory.defaultInstance().constructCollectionType(List.class, Staff.class));
        sos.addStaffInfo(staffList.get(0));
        return ReData.success();
    }

    /**
     * 删除单条数据
     *
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
     *
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
     *
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
     *
     * @param req
     * @return ReData
     */
    private ReData queryStaffList(ServletRequest req) {
        logger.info(req.getAttribute("page"));
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

        logger.info("干员姓名：" + name + "\t" + "职业：" + career + "\t" + "派别：" + faction);
        if (StringUtils.isEmpty(rarityString)) {
            return new ReData(1, "干员星级不能为空！");
        }
        List<Rarity> rarityList;
        try {
            rarityList = new ObjectMapper().readValue(rarityString,
                    TypeFactory.defaultInstance().constructCollectionType(List.class, Rarity.class));
        } catch (Exception e) {
            logger.error("json解析异常", e);
            return new ReData(1, "json解析异常！");
        }
        logger.info("参数获取完成");
        ArrayList<Staff> staff = sos
                .queryStaffListByCon((startIndex - 1) * queryCount, queryCount, name, career, faction, rarityList);
        logger.info(staff);
        return ReData.success().addInfo("total", sos.staffCount()).addInfo("staffList", staff);
    }
}
