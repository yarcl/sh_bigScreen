package com.xqkj.action;

import com.xqkj.bean.RazorUser;
import com.xqkj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2017/9/20.
 */
@Controller
@RequestMapping("/user")
@SessionAttributes({"user"})
public class UserAction {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/editPersonInfo.do", method = RequestMethod.POST)
    public ModelAndView editUserInfo(RazorUser user, ModelAndView mav){

        if(user!=null){
            int result = userService.updateUser(user);
            user = userService.getUserById(user.getUserId());
            mav.addObject("user", user);
        }
        mav.setViewName("/page/user/personInfo.jsp");

        return mav;
    }

    @RequestMapping(value = "/editPassword.do", method = RequestMethod.POST)
    public ModelAndView editPassword(String newPwd, String userId, ModelAndView mav){

        //System.out.println(newPwd);

        userService.updatePassword(userId, newPwd);
        mav.setViewName("/page/user/personInfo.jsp");

        return mav;
    }

}
