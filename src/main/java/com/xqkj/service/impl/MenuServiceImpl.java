package com.xqkj.service.impl;

import com.xqkj.bean.PageBean;
import com.xqkj.bean.RazorMenu;
import com.xqkj.dao.MenuDao;
import com.xqkj.service.MenuService;
import com.xqkj.util.CaculatePage;

import java.util.List;

public class MenuServiceImpl implements MenuService {

    private MenuDao menuDao;

    public MenuDao getMenuDao() {
        return menuDao;
    }

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    public List<RazorMenu> allMenuInfo(PageBean pageBean) {
        return menuDao.allMenuInfo(CaculatePage.caculateStart(pageBean), CaculatePage.caculateEnd(pageBean));
    }

    public int getMenuCount() {
        return menuDao.getMenuCount();
    }
}
