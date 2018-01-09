package com.xqkj.action;

import com.xqkj.bean.RazorProduct;
import com.xqkj.bean.RazorUser;
import com.xqkj.service.RazorProductService;
import com.xqkj.service.UserService;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
@SessionAttributes("user")
public class LoginAction{

    @Autowired
    private UserService userService;

    @RequestMapping("login.do")
    public ModelAndView login(String username, String password, ModelAndView mav){
        try{
            RazorUser user = userService.login(username,password);
            if(user!=null){
                user.setLoginPwd("");
                mav.addObject("user", user);
                mav.setViewName("index");
            } else {
                mav.setViewName("login");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return mav;
    }
    @RequestMapping("/loginOut.do")
    public ModelAndView loginOut(ModelAndView mav, HttpServletRequest request){
        try{
            HttpSession session = request.getSession();
            if(session!=null){
                session.removeAttribute("user");
            }
            mav.setViewName("/login");
        } catch (Exception e){
            e.printStackTrace();
        }
        return mav;
    }
}
