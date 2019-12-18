package service;

import dao.StaffOperationDao;
import entity.Staff;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @description:
 * @author: Tong
 * @date: 2019-12-16 19:58
 */
public class StaffOperationService {
    Logger logger = Logger.getLogger(StaffOperationService.class);
    StaffOperationDao sod = new StaffOperationDao();

    public StaffOperationService() {
        logger.info("=====service层=====");
    }

    public void addStaffInfo(Staff staff) throws ClassNotFoundException, SQLException, IOException {
        sod.addStaffInfo(staff);
    }

    public void removeStaffInfo(int id) throws ClassNotFoundException, SQLException, IOException {
        sod.removeStaffInfo(id);
    }

    public void updateStaffInfo(Staff staff) throws ClassNotFoundException, SQLException, IOException {
        sod.updateStaffInfo(staff);
    }

    public Staff queryStaffInfo(int id) throws ClassNotFoundException, SQLException, IOException {
        return sod.queryStaffInfo(id);
    }

    public ArrayList<Staff> queryStaffList() throws ClassNotFoundException, SQLException, IOException {
        return sod.queryStaffList();
    }
}
