package com.atlandes.msg.service;

import com.atlandes.msg.dao.MessageMapper;
import com.atlandes.msg.po.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * 类描述：邮件管理业务逻辑层
 * Created by Liucong on 2017/6/8.
 */
@Service
public class MsgService {
    private static final int resultValue = 1;
    @Autowired
    private MessageMapper messageMapper;

    //查看未读邮件列表
    public List<Message> unRead(int receivedUserId){
        List<Message> list = messageMapper.getUnReadListById(receivedUserId);
        return list;
}
    //更新为已读
    public int isRead(int receivedUserId){
        Date msgReceivedTime = new Date();
        long isRead = 1;
        Message msg = new Message();
        msg.setId(receivedUserId);
        msg.setIsRead(isRead);
        msg.setMsgReceivedTime(msgReceivedTime);
        messageMapper.isRead(msg);
        return resultValue;
    }

}

