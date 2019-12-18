package utils;

/**
 * @description:
 * @author: Tong
 * @date: 2019-12-16 20:30
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JSONUtil {
    /**
     * bean对象转json字符串
     *
     * @param obj
     * @param dataFormatString
     * @return
     */
    public static String beanToJson(Object obj, String dataFormatString) {
        if (obj != null) {
            if (dataFormatString == null) {
                return JSONObject.toJSONString(obj);
            }
            return JSONObject.toJSONStringWithDateFormat(obj, dataFormatString);
        }
        return null;
    }

    /**
     * bean对象转json字符串
     *
     * @param obj
     * @return
     */
    public static String beanToJson(Object obj) {
        if (obj != null) {
            return JSONObject.toJSONString(obj);
        }
        return null;
    }

    /**
     * string字符串转成json字符串
     *
     * @param key
     * @param value
     * @return
     */
    public static String stringToJonByFastjson(String key, String value) {
        if (key == null || value == null) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        map.put(key, value);
        return beanToJson(map);
    }

    /**
     * json字符串转bean对象
     *
     * @param json
     * @param clazz
     * @return
     */
    public static Object stringToBean(String json, Object clazz) {
        if (json == null || clazz == null) {
            return null;
        }
        return JSONObject.parseObject(json, clazz.getClass());
    }

    /**
     * json字符串转换成map对象
     *
     * @param json
     * @return
     */
    public static Map<String, Object> jsonToMap(String json) {
        if (json == null) {
            return null;
        }
        return JSONObject.parseObject(json, Map.class);
    }


    /**
     * 判断字符串是否可以转化为JSON数组
     *
     * @param content
     * @return
     */
    public static boolean isJsonArray(String content) {
        if (content == null)
            return false;
        try {
            JSONArray jsonStr = JSONArray.parseArray(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断字符串是否可以转化为json对象
     *
     * @param content
     * @return
     */
    public static boolean isJsonObject(String content) {
        if (content == null)
            return false;
        try {
            JSONObject jsonStr = JSONObject.parseObject(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取json字符串中所有的key值
     *
     * @param jsonString
     * @return
     */
    public static Set<String> getAllKeys(String jsonString) {
        Set<String> set = new HashSet<>();
        //按照","将json字符串分割成String数组
        String[] keyValue = jsonString.split(",");
        for (int i = 0; i < keyValue.length; i++) {
            String s = keyValue[i];
            //找到":"所在的位置，然后截取
            int index = s.indexOf(":");
            if (index == -1) {
                return set;
            }
            //第一个字符串因带有json的"{"，需要特殊处理
            if (i == 0) {
                set.add(s.substring(2, index - 1));
            } else {
                set.add(s.substring(1, index - 1));
            }
        }
        return set;
    }


}
