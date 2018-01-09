package com.xqkj.dao;

import com.xqkj.bean.RazorUser;

import java.util.List;

public interface UserDao {
    public RazorUser login(String username, String password);

    public int updateUser(RazorUser user);

    public RazorUser getUserById(int id);

    public int updatePassword(String userId, String newPwd);

    public List<RazorUser> getAllUsersInfo(int startPage, int endPage);

    public int getUserCount();
}
