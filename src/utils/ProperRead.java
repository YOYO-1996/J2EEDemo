package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @description:
 * @author: Tong
 * @date: 2019-12-16 22:27
 */
public class ProperRead {
    static private Properties props;

    static public String getProperty(String key) throws IOException {
        props.load(new FileInputStream("database.properties"));
        // 使用properties对象加载输入流
        //获取key对应的value值
        return props.getProperty(key);
    }
}
