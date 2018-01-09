package com.xqkj.dao;

import com.xqkj.bean.RazorMenu;

import java.util.List;

public interface MenuDao {


    public List<RazorMenu> allMenuInfo(int startPage, int endPage);

    public int getMenuCount();
}
