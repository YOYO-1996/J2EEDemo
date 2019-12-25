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

    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
    public static void closeConn(PreparedStatement ps,Connection conn){
        try{
            ps.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
