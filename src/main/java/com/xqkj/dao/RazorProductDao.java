package com.xqkj.dao;

import com.xqkj.bean.RazorProduct;

import java.util.List;

/**
 * Created by Administrator on 2017/9/18.
 */
public interface RazorProductDao {

    public List<RazorProduct> getRazorProductByUserId(String userId, int startPage, int endPage);


    public int getProductCount();
}
