package com.atlandes.auth.controller;

import com.alibaba.fastjson.JSON;
import com.atlandes.auth.po.User;
import com.atlandes.auth.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by XD.Wang on 2017/6/2.
 * 用户信息
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping("/getUser")
    @ResponseBody
    public String selectByPrimaryKey(Long id) {
        User u = userService.selectByPrimaryKey(id);
        return JSON.toJSONString(u);
    }

}
