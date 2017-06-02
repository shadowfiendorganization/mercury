package com.atlandes.auth.dao;

import com.atlandes.auth.po.RolePermissionRelations;

import java.util.List;
import java.util.Map;

public interface RolePermissionReMapper {

    int insert(RolePermissionRelations record);

    int insertSelective(RolePermissionRelations record);

    List<RolePermissionRelations> findRolePermissionByPid(Long id);

    List<RolePermissionRelations> findRolePermissionByRid(Long id);

    List<RolePermissionRelations> find(RolePermissionRelations entity);

    int deleteByPid(Long id);

    int deleteByRid(Long id);

    int delete(RolePermissionRelations entity);

    int deleteByRids(Map<String, Object> resultMap);

}