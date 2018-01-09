package com.xqkj.service;

import com.xqkj.bean.PageBean;
import com.xqkj.bean.RazorUser;

import java.util.List;

public interface UserService {
    public RazorUser login(String username, String password);

    public int updateUser(RazorUser user);

    public RazorUser getUserById(int id);

    public int updatePassword(String userId, String newPwd);

    public List<RazorUser> getAllUsersInfo(PageBean pageBean);

    public int getUserCount();
}
