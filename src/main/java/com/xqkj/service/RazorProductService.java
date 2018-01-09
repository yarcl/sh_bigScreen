package com.xqkj.service;

import com.xqkj.bean.PageBean;
import com.xqkj.bean.RazorProduct;

import java.util.List;

/**
 * Created by Administrator on 2017/9/18.
 */
public interface RazorProductService {

    public List<RazorProduct> getRazorProductByUserId(String userId, PageBean pageBean);

    public int getProductCount();
}
