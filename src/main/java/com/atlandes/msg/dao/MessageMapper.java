package com.atlandes.msg.dao;

import com.atlandes.msg.po.Message;

import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKeyWithBLOBs(Message record);

    int updateByPrimaryKey(Message record);

    List getMsgList();

    List getMsgListById(int id);

    //标记为已读
    int isRead(Message message);
    //获取未读邮件列表
    List<Message> getUnReadListById(int receivedUserId);
}