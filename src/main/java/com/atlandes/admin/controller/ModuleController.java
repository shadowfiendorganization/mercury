package com.atlandes.admin.controller;

import com.alibaba.fastjson.JSON;
import com.atlandes.admin.po.Module;
import com.atlandes.admin.service.ModuleService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 类描述：系统基础信息管理
 * Created by Liucong on 2017/05/26.
 */
@Controller
@RequestMapping("module")
public class ModuleController {

    @Resource
    ModuleService moduleService;

    private static Logger log = Logger.getLogger(ModuleController.class);

    /**
     * 获取模块列表
     * @param
     * @return 模块列表
     */
    @ResponseBody
    @RequestMapping("getModuleList")
    public String selectModul(){
        List<Module> moudleList = moduleService.getMoudleList();
        return JSON.toJSONString(moudleList);
    }

    /**
     * 新增模块
     * @param module 模块
     * @return 状态值
     */
    @ResponseBody
    @RequestMapping("addModule")
    public int addModul(Module module){
        int a = moduleService.add(module);
        return a;
    }

    /**
     * 修改模块
     * @param module 模块
     * @return 状态值
     */
    @ResponseBody
    @RequestMapping("updModule")
    public int updModul(Module module){
        int a = moduleService.update(module);
        return a;
    }

    /**
     * 删除模块
     * @param id 模块
     * @return 状态值
     */
    @ResponseBody
    @RequestMapping("delModule")
    public int delModul(int id){
        moduleService.delete(id);
        return 1;
    }

    /**
     * 根据id查询模块
     * @param id
     * @Return baseInfo
     */
    @ResponseBody
    @RequestMapping("selectModuleById")
    public String selectModuleById(Integer id){
        Module module = moduleService.selectModuleById(id);
        return JSON.toJSONString(module);
    }
}
