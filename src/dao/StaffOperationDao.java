package dao;

import entity.Staff;
import utils.ProperRead;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * @description: 1.注册驱动(静态方法)(包名 + 类名 ）
 *2.获取连接对象 ( 导包都导sql里面的 ， 不导jdbc里的 ； 多态 ！ 报异常是因为用户输入的串可能写错 ） 后面设置下数据格式
 *3.获取语句执行平台 ：
 *4.执行SQL语句 ： 增一套语句执行一句sql语句 返回的是一个Int值 ， 是指执行了几行
 *6.释放资源 ( 先开后关)
 * @author: Tong
 * @date: 2019-12-16 20:02
 */
public class StaffOperationDao {

    //新增干员信息
    public void addStaffInfo(Staff staff) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(ProperRead.getProperty("url"), ProperRead.getProperty("user"), ProperRead.getProperty("password"));
        String sql = "INSERT INTO staff ( sta_name, sta_sex, sta_health, sta_attack_Power, sta_cost, sta_defence, sta_avoid_Num, sta_spell_Resistance, sta_rarity, sta_Redeploy_Speed, sta_attack_Speed, sta_career, sta_faction )" +
                "VALUES" +
                "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, staff.getStaName());
        ps.setString(2, staff.getStaSex());
        ps.setString(3, staff.getStaHealth());
        ps.setString(4, staff.getStaAttackPower());
        ps.setString(5, staff.getStaCost());
        ps.setString(6, staff.getStaDefence());
        ps.setString(7, staff.getStaAvoidNum());
        ps.setString(8, staff.getStaSpellResistance());
        ps.setString(9, staff.getStaRarity());
        ps.setString(10, staff.getStaRedeploySpeed());
        ps.setString(11, staff.getStaAttackSpeed());
        ps.setString(12, staff.getStaCareer());
        ps.setString(13, staff.getStaFaction());


        ps.execute();
        ps.close();
        conn.close();
    }

    //删除干员信息
    public void removeStaffInfo(int id) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(ProperRead.getProperty("url"), ProperRead.getProperty("user"), ProperRead.getProperty("password"));
        String sql = "delete from staff t where t.sta_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, id);

        ps.execute();
        ps.close();
        conn.close();
    }

    //修改干员信息
    public void updateStaffInfo(Staff staff) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(ProperRead.getProperty("url"), ProperRead.getProperty("user"), ProperRead.getProperty("password"));
        String sql = "";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, "");

        int row = ps.executeUpdate(sql);
        ps.close();
        conn.close();
    }

    //查询单个干员信息
    public Staff queryStaffInfo(int id) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(ProperRead.getProperty("url"), ProperRead.getProperty("user"), ProperRead.getProperty("password"));
        String sql = "";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, "");

        ResultSet rs = ps.executeQuery(sql);

        while (rs.next()) {
        }
        ps.close();
        conn.close();
        return new Staff();
    }

    //查询所有干员信息
    public ArrayList<Staff> queryStaffList() throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(ProperRead.getProperty("url"), ProperRead.getProperty("user"), ProperRead.getProperty("password"));
        String sql = "SELECT DISTINCT" +
                "sta_id," +
                "sta_name," +
                "sta_sex," +
                "sta_health," +
                "sta_attack_Power," +
                "sta_cost," +
                "sta_defence," +
                "sta_avoid_Num," +
                "sta_spell_Resistance," +
                "sta_rarity," +
                "sta_Redeploy_Speed," +
                "sta_attack_Speed," +
                "sta_career," +
                "sta_faction" +
                "FROM" +
                "staff";
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery(sql);

        while (rs.next()) {
        }
        ps.close();
        conn.close();
        return new ArrayList<Staff>();
    }
}
