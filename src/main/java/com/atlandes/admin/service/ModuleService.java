package com.atlandes.admin.service;

import com.atlandes.admin.dao.ModuleMapper;
import com.atlandes.admin.po.Module;
import org.springframework.jmx.export.annotation.ManagedNotification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：基础信息service层
 * Created by Liucong on 2017/05/26.
 */
@Service
public class ModuleService {

    @Resource
    private ModuleMapper moduleMapper ;

    //定义操作返回值
    private static final int resultValue = 1;
    //添加模块
    public int add(Module module){
        moduleMapper.insert(module);
        return resultValue;
    }

    //更新模块
    public int update(Module module){
        moduleMapper.updateByPrimaryKey(module);
        return resultValue;
    }

    //删除模块
    public int delete(Integer id){
        moduleMapper.deleteByPrimaryKey(id);
        return resultValue;
    }

    //获取模块列表
    public List getMoudleList(){
        List<Module> moduleList =  moduleMapper.getModuleList();
       return moduleList;
    }

    //根据id查询模块
    public Module selectModuleById(Integer id){
        Module module = moduleMapper.selectByPrimaryKey(id);
        return module;
    }
}
