package entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Tong
 * @date: 2019-12-16 20:10
 */
public class ReData{
    
    private int code;
    private String msg;
    private Map<String, Object> extendInfo = new HashMap<>();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(Map<String, Object> extendInfo) {
        this.extendInfo = extendInfo;
    }

    public static ReData success(){
        ReData res = new ReData();
        res.setCode(0);
        res.setMsg("操作成功！");
        return res;
    }

    public static ReData fail(){
        ReData res = new ReData();
        res.setCode(1);
        res.setMsg("操作失败！");
        return res;
    }

    public ReData addInfo(String key, Object obj){
        this.extendInfo.put(key, obj);
        return this;
    }

    @Override
    public String toString() {
        return "ReData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", extendInfo=" + extendInfo +
                '}';
    }
}
