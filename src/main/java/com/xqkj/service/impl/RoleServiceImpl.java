package com.xqkj.service.impl;

import com.xqkj.bean.PageBean;
import com.xqkj.bean.RazorRole;
import com.xqkj.dao.RoleDao;
import com.xqkj.service.RoleService;
import com.xqkj.util.CaculatePage;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public List<RazorRole> allRoleInfo(PageBean pageBean) {
        return roleDao.allRoleInfo(CaculatePage.caculateStart(pageBean), CaculatePage.caculateEnd(pageBean));
    }

    public int getRoleCount() {
        return roleDao.getRoleCount();
    }
}
