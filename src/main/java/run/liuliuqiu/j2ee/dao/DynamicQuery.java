package run.liuliuqiu.j2ee.dao;

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
    private static Logger logger = Logger.getLogger(DynamicQuery.class);

    private static String templet = " %s %s %s ?";//and sta_name = ?
    private static String inTemplet = " %s %s %s (";//and sta_name in (

    /**
     * @Description: 在sql后添加查询条件，支持in和=操作符
     * @Param: [sql, parameterList]
     * @Return: java.lang.String
     * @Date: 2019/12/27
     **/
    public static String generateSql(String sql, ArrayList<Parameter> parameterList) {
        StringBuffer buffer = new StringBuffer(sql);
        int flag = 1;
        for (Parameter p : parameterList) {
            //当前操作符为in
            if (p.getOperator().equals("in")) {
                //上个操作符不是in，添加in的头部
                if (flag == 1) {
                    buffer.append(String.format(inTemplet, p.getConcat(), p.getField(), p.getOperator()));
                } //上个操作符是in，添加问号
                buffer.append(" ?,");
                flag = 0;
            } else if (p.getOperator().equals("=")) {
                //上个操作符是in,去掉最后一个逗号，加上括号
                if (flag == 0) {
                    buffer = new StringBuffer(buffer.substring(0, buffer.length() - 1));
                    buffer.append(") ");
                }
                buffer.append(String.format(templet, p.getConcat(), p.getField(), p.getOperator()));
                flag=1;
            }
        }
        if(flag==0){
            buffer = new StringBuffer(buffer.substring(0, buffer.length() - 1));
            buffer.append(") ");
        }
        logger.info(buffer);
        return buffer.toString();
    }

    /**
     * @Description: 设置预编译的查询参数
     * @Param: [pst, parameterList, index]
     * @Return: int
     * @Date: 2019/12/27
     **/
    public static int fillPreStaComm(PreparedStatement pst, ArrayList<Parameter> parameterList, int index)  {
        for (Parameter p : parameterList) {
            index++;
            logger.info(p.getValue());
            try {
                pst.setObject(index, p.getValue());
            } catch (SQLException e) {
                logger.error("数据库操作异常",e);
            }
        }
        return index;
    }

}