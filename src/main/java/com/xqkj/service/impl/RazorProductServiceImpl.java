package com.xqkj.service.impl;

import com.xqkj.bean.PageBean;
import com.xqkj.bean.RazorProduct;
import com.xqkj.dao.RazorProductDao;
import com.xqkj.service.RazorProductService;
import com.xqkj.util.CaculatePage;

import java.util.List;

/**
 * Created by Administrator on 2017/9/18.
 */
public class RazorProductServiceImpl implements RazorProductService{

    private RazorProductDao razorProductDao;

    public RazorProductDao getRazorProductDao() {
        return razorProductDao;
    }

    public void setRazorProductDao(RazorProductDao razorProductDao) {
        this.razorProductDao = razorProductDao;
    }

    public int getProductCount() {
        return razorProductDao.getProductCount();
    }

    public List<RazorProduct> getRazorProductByUserId(String userId, PageBean pageBean) {
        return razorProductDao.getRazorProductByUserId(userId, CaculatePage.caculateStart(pageBean), CaculatePage.caculateEnd(pageBean));
    }
}
