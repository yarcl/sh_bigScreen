package com.xqkj.service;

import com.xqkj.bean.PageBean;
import com.xqkj.bean.RazorRole;

import java.util.List;

public interface RoleService {

    public List<RazorRole> allRoleInfo(PageBean pageBean);

    public int getRoleCount();
}
