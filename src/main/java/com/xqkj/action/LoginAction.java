package com.xqkj.action;


import com.alibaba.fastjson.JSONObject;
import com.xqkj.bean.RazorUser;

import com.xqkj.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class LoginAction{

    @Autowired
    private UserService userService;

    @RequestMapping(value="login.do", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public @ResponseBody Object login(String username, String password, HttpServletRequest request){
        JSONObject obj = new JSONObject();
        try{
            RazorUser user = userService.login(username,password);

            if(user!=null){
                user.setLoginPwd("");
                request.getSession().setAttribute("user", user);
                obj.put("flag", true);
            } else {
                obj.put("flag", false);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return obj;
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
