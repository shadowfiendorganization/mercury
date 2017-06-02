package com.atlandes.auth.shiro.session;

import com.atlandes.auth.shiro.cache.JedisManager;
import com.atlandes.auth.shiro.component.MercurySessionManager;
import com.atlandes.auth.shiro.util.SerializeUtils;
import com.atlandes.auth.shiro.util.ShiroLogUtils;
import org.apache.shiro.session.Session;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by XD.Wang on 2017/5/29.
 * Session 缓存管理
 */
@SuppressWarnings("unchecked")
public class JedisShiroSessionRepository implements ShiroSessionRepository {

    public static final String REDIS_SHIRO_SESSION = "mercury-shiro-session:";
    public static final String REDIS_SHIRO_ALL = "*mercury-shiro-session:*";
    private static final int SESSION_VAL_TIME_SPAN = 18000;
    private static final int REDIS_SESSION_DB_INDEX = 1;

    private JedisManager jedisManager;

    @Override
    public void saveSession(Session session) {
        if (session == null || session.getId() == null)
            throw new NullPointerException("session is empty");
        try {
            if (null == session.getAttribute(MercurySessionManager.SESSION_STATUS)) {
                SessionMetaInfo sessionStatus = new SessionMetaInfo();
                session.setAttribute(MercurySessionManager.SESSION_STATUS, sessionStatus);
            }
            // 设置超时时间
            byte[] value = SerializeUtils.serialize(session);
            long sessionTimeOut = session.getTimeout() / 1000;
            Long expireTime = sessionTimeOut + SESSION_VAL_TIME_SPAN + (5 * 60);
            byte[] key = SerializeUtils.serialize(buildRedisSessionKey(session.getId()));
            // 将Session放入缓存：数据库索引、Session Key、序列化后的Session、超时时间
            getJedisManager().saveValueByKey(REDIS_SESSION_DB_INDEX, key, value, expireTime.intValue());
        } catch (Exception e) {
            ShiroLogUtils.fmtError(getClass(), e, "save session error，id:[%s]", session.getId());
        }
    }

    @Override
    public void deleteSession(Serializable id) {
        if (id == null) {
            throw new NullPointerException("session id is empty");
        }
        try {
            getJedisManager().deleteByKey(REDIS_SESSION_DB_INDEX,
                    SerializeUtils.serialize(buildRedisSessionKey(id)));
        } catch (Exception e) {
            ShiroLogUtils.fmtError(getClass(), e, "删除session出现异常，id:[%s]", id);
        }
    }

    @Override
    public Session getSession(Serializable id) {
        if (id == null)
            throw new NullPointerException("session id is empty");
        Session session = null;
        try {
            byte[] value = getJedisManager().getValueByKey(REDIS_SESSION_DB_INDEX, SerializeUtils
                    .serialize(buildRedisSessionKey(id)));
            session = SerializeUtils.deserialize(value, Session.class);
        } catch (Exception e) {
            ShiroLogUtils.fmtError(getClass(), e, "获取session异常，id:[%s]", id);
        }
        return session;
    }

    @Override
    public Collection<Session> getAllSessions() {
        Collection<Session> sessions = null;
        try {
            sessions = getJedisManager().AllSession(REDIS_SESSION_DB_INDEX, REDIS_SHIRO_SESSION);
        } catch (Exception e) {
            ShiroLogUtils.fmtError(getClass(), e, "获取全部session异常");
        }

        return sessions;
    }

    private String buildRedisSessionKey(Serializable sessionId) {
        return REDIS_SHIRO_SESSION + sessionId;
    }

    public JedisManager getJedisManager() {
        return jedisManager;
    }

    public void setJedisManager(JedisManager jedisManager) {
        this.jedisManager = jedisManager;
    }
}
