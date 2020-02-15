package service;

import dao.UserDao;
import entity.UserInfo;

/**
 * @author ：Tong
 * @date ：Created in 2020/1/28 20:10
 * @description：
 * @version: $
 */
public class LoginService {
    UserDao userDao = new UserDao();

    public UserInfo queryUserInfo(String userMobile) {
        return userDao.queryUserInfo(userMobile);
    }

    public void loginOn(String mobile, String Pwd) {
        //判断用户是否存在

        //判断用户状态

        //判断密码是否正确

        //记录登录时间

    }
}
