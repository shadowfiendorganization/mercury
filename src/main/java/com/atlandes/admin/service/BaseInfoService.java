package com.atlandes.admin.service;

import com.atlandes.admin.dao.BaseInfoMapper;
import com.atlandes.admin.po.BaseInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * 类描述：基础信息service层
 * Created by Liucong on 2017/05/26.
 */
@Service
public class BaseInfoService {

    @Resource
    private BaseInfoMapper baseInfoMapper;

    //添加模块
    public int add(BaseInfo baseInfo){
        baseInfoMapper.add(baseInfo);
        return 1;
    }

    //更新模块
    public int update(BaseInfo baseInfo){
        baseInfoMapper.update(baseInfo);
        return 1;
    }

    //删除模块
    public int delete(Long id){
        baseInfoMapper.delete(id);
        return 1;
    }

    //获取模块列表
    public ArrayList getMoudleList(){
        ArrayList<BaseInfo> moudleList =  baseInfoMapper.getMoudle();
       return moudleList;
    }

    //根据id查询模块
    public BaseInfo selectModuleById(Long id){
        BaseInfo baseInfo = baseInfoMapper.selectModuleById(id);
        return baseInfo;
    }
}
