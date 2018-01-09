package com.xqkj.service;

import com.xqkj.bean.PageBean;
import com.xqkj.bean.RazorMenu;

import java.util.List;

public interface MenuService {
    public List<RazorMenu> allMenuInfo(PageBean pageBean);

    public int getMenuCount();
}
