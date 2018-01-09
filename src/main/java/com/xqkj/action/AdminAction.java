package com.xqkj.action;

import com.xqkj.bean.PageBean;
import com.xqkj.bean.RazorUser;
import com.xqkj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminAction {

    @Autowired
    private UserService userService;

    @Autowired
    private PageBean pageBean;

    @RequestMapping("/allAdminUsers.do")
    public ModelAndView allUsersInfo(ModelAndView mav, String nowPage){
        if(nowPage!=null && !"".equals(nowPage)){
            pageBean.setNowPage(Integer.parseInt(nowPage));
        }
        List<RazorUser> userList = userService.getAllUsersInfo(pageBean);
        pageBean.setCount(userService.getUserCount());
        mav.addObject("page", pageBean);
        mav.addObject("userList", userList);
        mav.setViewName("/page/admin/adminUsersInfo.jsp");
        return mav;
    }

}
