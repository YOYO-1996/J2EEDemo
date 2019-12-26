package dao;

import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @description:
 * @author: Tong
 * @date: 2019-12-26 20:03
 */
public class DynamicQuery {
    private static Logger logger= Logger.getLogger(DynamicQuery.class);

    private static String templet = " %s %s %s ?";//and sta_name = ?
    private static String inTemplet = " %s %s %s (";//and sta_name in (

    public static String generateSql(String sql, ArrayList<Parameter> parameterList) {
        StringBuffer buffer = new StringBuffer(sql);
        for (Parameter p : parameterList) {
            buffer.append(String.format(templet, p.getConcat(),p.getField(), p.getOperator()));
        }
        logger.debug(buffer);
        return buffer.toString();
    }

    public static String generateSqlWithIn(String sql, ArrayList<Parameter> parameterList) {
        StringBuffer buffer = new StringBuffer(sql);
        Parameter p = parameterList.get(0);

        buffer.append(String.format(inTemplet, p.getConcat(), p.getField(), p.getOperator()));
        for (int i = 0; i < parameterList.size(); i++) {
            buffer.append("?,");
        }
        buffer.append(")");
        logger.debug(buffer);
        return buffer.substring(buffer.length() - 3, buffer.length() - 2);//去掉最后一个逗号
    }

    /**
     * 通用操作
     */
    public static int fillPreStaComm(PreparedStatement pst, ArrayList<Parameter> parameterList, int index) throws SQLException {
        for (Parameter p : parameterList) {
            index++;
            pst.setObject(index, p.getValue());
        }
        return index;
    }

}