package utils;

/**
 * @author ：Tong
 * @date ：Created in 2019/12/26 16:13
 * @description：
 * @version: $
 */
public class StringUtils {

    public static boolean isEmpty(String str){
        if(null==str||"".equals(str)){
            return true;
        }
        else{
            return false;
        }
    }
}
