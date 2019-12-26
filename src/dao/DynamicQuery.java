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

    private String templet = " %s %s %s ?";
    private String baseSql;
    private ArrayList<Parameter> parameters = new ArrayList<Parameter>();

    public DynamicQuery() {

    }

    /**
     * 要求baseSql带有where条件
     *
     * @param baseSql
     */
    public void setBaseSql(String baseSql) {
        this.baseSql = baseSql;
    }

    public void addParameter(Parameter parameter) {
        parameters.add(parameter);
    }

    public String generateSql() {
        StringBuffer buffer = new StringBuffer(baseSql);
        for (Parameter p : parameters) {
            buffer.append(String.format(templet, p.getConcat(),p.getField(), p.getOperator()));
        }
        logger.debug(buffer);
        return buffer.toString();
    }

    public void fillPreparedStatement(PreparedStatement pst) throws SQLException {
        int count = 1;
        for (Parameter p : parameters) {
            pst.setObject(count, p.getValue());
            count++;
        }
    }
    /**
     * 对in的单独操作
     */


}