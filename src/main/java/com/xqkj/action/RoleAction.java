package com.xqkj.action;

import com.xqkj.bean.PageBean;
import com.xqkj.bean.RazorRole;
import com.xqkj.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleAction {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PageBean pageBean;

    @RequestMapping("/allRoleInfo.do")
    public ModelAndView allRoleInfo(ModelAndView mav, String nowPage){
        if(nowPage!=null && !"".equals(nowPage)){
            pageBean.setNowPage(Integer.parseInt(nowPage));
        }
        List<RazorRole> roleList = roleService.allRoleInfo(pageBean);
        pageBean.setCount(roleService.getRoleCount());
        mav.addObject("page", pageBean);
        mav.addObject("roleList", roleList);
        mav.setViewName("/page/role/allRoleInfo.jsp");
        return mav;
    }
}
