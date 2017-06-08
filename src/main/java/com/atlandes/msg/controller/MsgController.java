package com.atlandes.msg.controller;

import com.atlandes.msg.service.MsgService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 类描述：消息模块controller类
 * Created by liucong on 2017/6/8.
 */
@Controller
@RequestMapping("msg")
public class MsgController {

    private static Logger log = Logger.getLogger(MsgController.class);
    //定义操作返回值
    private static final int resultValue = 1;

    @Autowired
    private MsgService msgService;

    /**
     * 方法描述：阅读邮件，标记为已读
     * @param  receivedUserId
     * @return 状态值
     */
    @ResponseBody
    @RequestMapping("readMsg")
    public int isRead(int receivedUserId){
        return msgService.isRead(receivedUserId);
    }

    /**
     * 方法描述：获取未读邮件
     * @param receivedUserId
     * @return list列表
     */
    @ResponseBody
    @RequestMapping("getUnRead")
    public List unRead(int receivedUserId) {
        return msgService.unRead(receivedUserId);
    }

/*    @ResponseBody
    @RequestMapping("add")
    public int add(Message message){
        messageMapper.insert(message);
        return resultValue;
    }

    @ResponseBody
    @RequestMapping("getMsgById")
    public Message getMessage(int id){
        Message msg = messageMapper.selectByPrimaryKey(id);
        return msg;
    }

    @ResponseBody
    @RequestMapping("getMsgList")
    public List<Message> getMessageList(){
        List<Message> list = messageMapper.getMsgList();
        return list;
    }

    @ResponseBody
    @RequestMapping("delect")
    public int delect(int id){
        messageMapper.deleteByPrimaryKey(id);
        return resultValue;
    }
    @ResponseBody
    @RequestMapping("update")
    public int update(Message message){
        messageMapper.updateByPrimaryKey(message);
        return resultValue;
    }*/
}
