package com.atlandes.auth.shiro.session;

import org.apache.shiro.session.Session;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by XD.Wang on 2017/5/27.
 * Session信息操作
 */
public interface ShiroSessionRepository {

    /**
     * 存储Session
     *
     * @param session 会话
     */
    void saveSession(Session session);

    /**
     * 删除session
     *
     * @param sessionId 会话ID
     */
    void deleteSession(Serializable sessionId);

    /**
     * 获取session
     *
     * @param sessionId 会话ID
     * @return 会话
     */
    Session getSession(Serializable sessionId);

    /**
     * @return 所有会话
     */
    Collection<Session> getAllSessions();

}
