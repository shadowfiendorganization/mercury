package com.atlandes.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Administrator on 2017/6/2.
 */
@Controller
@RequestMapping("main")
public class AdminController {

    @RequestMapping("mainPage")
    @ResponseBody
    public ModelAndView mainPage(){
        ModelAndView mv = new ModelAndView("admin/adminMain");

        return mv;
    }


    @RequestMapping("adminLeft")
    @ResponseBody
    public ModelAndView adminLeft(){
        ModelAndView mv = new ModelAndView("admin/adminLeft");

        return mv;
    }
}
