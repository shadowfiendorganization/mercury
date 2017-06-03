package com.atlandes.admin.controller;

import com.alibaba.fastjson.JSON;
import com.atlandes.admin.po.BaseInfo;
import com.atlandes.admin.service.BaseInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("BaseInfo")
public class BaseInfoController {

    @Resource
    BaseInfoService baseInfoService;

    private static Logger log = Logger.getLogger(BaseInfoController.class);

    /**
     * 获取模块列表
     * @param
     * @return 模块列表
     */
    @ResponseBody
    @RequestMapping("selectModule")
    public String selectModul(){
        List<BaseInfo> moudleList = baseInfoService.getMoudleList();
        return JSON.toJSONString(moudleList);
    }

    /**
     * 新增模块
     * @param baseInfo 模块
     * @return 状态值
     */
    @ResponseBody
    @RequestMapping("addModule")
    public int addModul(BaseInfo baseInfo){
        int a = baseInfoService.add(baseInfo);
        return a;
    }

    /**
     * 修改模块
     * @param baseInfo 模块
     * @return 状态值
     */
    @ResponseBody
    @RequestMapping("updModule")
    public int updModul(BaseInfo baseInfo){
        int a = baseInfoService.update(baseInfo);
        return a;
    }

    /**
     * 删除模块
     * @param id 模块
     * @return 状态值
     */
    @ResponseBody
    @RequestMapping("delModule")
    public int delModul(Long id){
        baseInfoService.delete(id);
        return 1;
    }

    /**
     * 根据id查询模块
     * @param id
     * @Return baseInfo
     */
    @ResponseBody
    @RequestMapping("selectModuleById")
    public String selectModuleById(Long id){
        BaseInfo moudle = baseInfoService.selectModuleById(id);
        return JSON.toJSONString(moudle);
    }
}
