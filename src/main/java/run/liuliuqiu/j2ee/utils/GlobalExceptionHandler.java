package run.liuliuqiu.j2ee.utils;

import org.apache.log4j.Logger;
import run.liuliuqiu.j2ee.entity.ReData;


/**
 * @author ：Tong
 * @date ：Created in 2019/12/30 9:53
 * @description：
 * @version: $
 */
public class GlobalExceptionHandler {
    Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    public ReData staffExceptionHandler(StaffException e) {
        return new ReData(1, e.getMessage());
    }

    public ReData exceptionHandler(Exception e) {
        logger.error(e);
        return new ReData(1, "请求异常！");
    }
}
