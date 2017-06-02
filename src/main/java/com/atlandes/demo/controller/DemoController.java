package com.atlandes.demo.controller;

import com.alibaba.fastjson.JSON;
import com.atlandes.demo.po.Demo;
import com.atlandes.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by XD.Wang on 2017/5/23.
 * 示例控制器
 */
@Controller
@RequestMapping("com/atlandes/demo")
public class DemoController {

    private final DemoService demoService;

    @Autowired
    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @RequestMapping(value = "list", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getDemoList() {
        List<Demo> list = demoService.getDemoList();
        return JSON.toJSONString(list);
    }

}
