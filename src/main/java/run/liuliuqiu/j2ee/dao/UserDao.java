package run.liuliuqiu.j2ee.dao;

import org.apache.log4j.Logger;
import run.liuliuqiu.j2ee.entity.UserInfo;
import run.liuliuqiu.j2ee.utils.DbConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author ：Tong
 * @date ：Created in 2020/1/28 20:37
 * @description：
 * @version: $
 */
public class UserDao {
    Logger logger = Logger.getLogger(UserDao.class);

    public UserInfo queryUserInfo(String userMobile) {

        ArrayList<Parameter> parameters = new ArrayList<>();

        parameters.add(new Parameter("and", "pui_user_mobile", "=", userMobile));

        UserInfo userInfo = new UserInfo();
        try {
            Connection conn = DbConn.getConn();
            String sql = SQLString.queryUserMain;
            sql = DynamicQuery.generateSql(sql, parameters);
            logger.info(sql);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userMobile);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userInfo.setUserId(rs.getString("pui_user_id"));
                userInfo.setUserMobile(rs.getString("pui_user_mobile"));
                userInfo.setUserEmail(rs.getString("pui_user_email"));
                userInfo.setUserName(rs.getString("pui_user_name"));
                userInfo.setUserStt(rs.getInt("pui_user_stt"));
            }

            DbConn.closeConn(ps, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    public UserInfo queryUserInfoByEmail(String userEmail) {

        return new UserInfo();
    }

    public UserInfo queryUserInfoByUserName(String userName) {
        return new UserInfo();
    }


}
