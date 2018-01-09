package com.xqkj.util;

import com.xqkj.bean.PageBean;

public class CaculatePage {

    public static int caculateStart(PageBean pageBean){
        return (pageBean.getNowPage()-1)*pageBean.getPageSize();
    }

    public static int caculateEnd(PageBean pageBean){
        return pageBean.getNowPage()*pageBean.getPageSize();
    }

}
