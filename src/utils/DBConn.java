package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author ：Tong
 * @date ：Created in 2019/12/25 15:33
 * @description：
 * @version: $
 */
public class DBConn {
    static final String url = "jdbc:mysql://49.235.184.120:6666/ArkNights?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
    static final String user = "root";
    static final String password = "Liuliuqiu123";

    /**
     * @Description: 1.注册驱动(静态方法)(包名 + 类名 ）
     * 2.获取连接对象 ( 导包都导sql里面的 ， 不导jdbc里的 ； 多态 ！ 报异常是因为用户输入的串可能写错 ）
     * @Param: []
     * @Return: java.sql.Connection
     * @Date: 2019/12/26
     **/
    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    /**
    * @Description: 3.获取语句执行平台
     * @Param: [conn, sql]
    * @Return: java.sql.PreparedStatement
    * @Date: 2019/12/26
    **/
    public static PreparedStatement prepareSta(Connection conn, String sql) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }
    /**
    * @Description: 1、2、3步合并
    * @Param: [sql]
    * @Return: java.sql.PreparedStatement
    * @Date: 2019/12/26
    **/
    public static PreparedStatement connAndPreSta(String sql) {
        PreparedStatement ps = null;
        try {
            Connection conn = getConn();
            ps = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }
    /**
    * @Description: 6.释放资源 ( 先开后关)
    * @Param: [ps, conn]
    * @Return: void
    * @Date: 2019/12/26
    **/
    public static void closeConn(PreparedStatement ps, Connection conn) {
        try {
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
