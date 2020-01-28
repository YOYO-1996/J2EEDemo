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

    public UserInfo queryUserInfo(int userMobile){
        return userDao.queryUserInfo(userMobile);
    }
}
