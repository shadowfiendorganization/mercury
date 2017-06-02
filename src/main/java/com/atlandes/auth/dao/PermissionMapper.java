package com.atlandes.auth.dao;

import com.atlandes.auth.po.Permission;

import java.util.List;
import java.util.Set;

public interface PermissionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> selectPermissionById(Long id);

    Set<String> findPermissionUrlByUserId(Long id);

}