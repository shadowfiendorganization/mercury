package com.atlandes.admin.dao;

import com.atlandes.admin.po.BaseInfo;

import java.util.ArrayList;


/**
 * Created by TOSHIBA on 2017/05/26.
 */
public interface BaseInfoMapper {
    int add(BaseInfo baseInfo);
    int delete(Long id);
    int update(BaseInfo baseInfo);
    ArrayList getMoudle();
    BaseInfo selectModuleById(Long id);
}
