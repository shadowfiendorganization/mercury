package com.atlandes.auth.dao;

import com.atlandes.auth.po.UserRoleRelations;

import java.util.List;
import java.util.Map;

public interface UserRoleReMapper {

    int insert(UserRoleRelations record);

    int insertSelective(UserRoleRelations record);

    int deleteByUserId(Long id);

    int deleteRoleByUserIds(Map<String, Object> resultMap);

    List<Long> findUserIdByRoleId(Long id);

}