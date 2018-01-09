package com.xqkj.service.impl;

import com.xqkj.bean.PageBean;
import com.xqkj.bean.RazorUser;
import com.xqkj.dao.UserDao;
import com.xqkj.service.UserService;
import com.xqkj.util.CaculatePage;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public RazorUser login(String username, String password) {
        return userDao.login(username, password);
    }

    public List<RazorUser> getAllUsersInfo(PageBean pageBean) {
        return userDao.getAllUsersInfo(CaculatePage.caculateStart(pageBean), CaculatePage.caculateEnd(pageBean));
    }

    public int updateUser(RazorUser user) {
        return userDao.updateUser(user);
    }

    public RazorUser getUserById(int id) {
        return userDao.getUserById(id);
    }

    public int getUserCount() {
        return userDao.getUserCount();
    }

    public int updatePassword(String userId, String newPwd) {
        return userDao.updatePassword(userId, newPwd);
    }
}
