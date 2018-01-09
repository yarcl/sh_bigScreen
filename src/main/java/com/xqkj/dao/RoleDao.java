package com.xqkj.dao;

import com.xqkj.bean.RazorRole;

import java.util.List;

public interface RoleDao {
    public List<RazorRole> allRoleInfo(int startPage, int endPage);

    public int getRoleCount();
}
