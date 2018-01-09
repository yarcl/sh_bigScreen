package com.xqkj.action;

import com.xqkj.bean.PageBean;
import com.xqkj.bean.RazorProduct;
import com.xqkj.service.RazorProductService;
import com.xqkj.util.PageTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Administrator on 2017/9/19.
 */
@Controller
@RequestMapping("/")
public class ProductAction {
    @Autowired
    private RazorProductService razorProductService;

    @Autowired
    private PageBean pageBean;

    @RequestMapping("/myProductInfo.do")
    public ModelAndView allProductInfo(ModelAndView mav, String userId, String nowPage){
        if(nowPage!=null && !"".equals(nowPage)){
            pageBean.setNowPage(Integer.parseInt(nowPage));
        }
        List<RazorProduct> razorProductList = razorProductService.getRazorProductByUserId(userId, pageBean);
        pageBean.setCount(razorProductService.getProductCount());
        mav.addObject("razorProductList", razorProductList);
        mav.addObject("page", pageBean);
        mav.setViewName("page/product/productList.jsp");
        return mav;
    }
}
