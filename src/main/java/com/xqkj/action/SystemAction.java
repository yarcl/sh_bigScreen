package com.xqkj.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;

@RequestMapping("/system")
@Controller
public class SystemAction {

    @RequestMapping("/systemInfo.do")
    public ModelAndView catchSystemInfo(ModelAndView mav){



        return mav;
    }

}
