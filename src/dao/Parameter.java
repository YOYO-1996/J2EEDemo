package dao;

/**
 * @description: sql查询参数类
 * @author: Tong
 * @date: 2019-12-26 20:02
 */
public class Parameter {
    private String concat;
    private String field;
    private Object value;
    private String operator;

    /**
     *
     * @param concat 连接符：and，or
     * @param field 数据列名
     * @param operator 操作符：=，<>，like，in
     * @param value 列值
     */
    public Parameter(String concat, String field, String operator, Object value) {
        this.concat = concat;
        this.field = field;
        this.operator = operator;
        this.value = value;
    }

    public String getConcat() {
        return concat;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }

    public String getOperator() {
        return operator;
    }
}

