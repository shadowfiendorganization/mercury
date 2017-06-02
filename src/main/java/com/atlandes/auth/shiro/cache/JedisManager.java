package com.atlandes.auth.shiro.cache;

import com.atlandes.auth.shiro.session.JedisShiroSessionRepository;
import com.atlandes.auth.shiro.util.SerializeUtils;
import com.atlandes.auth.shiro.util.ShiroLogUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Redis客户端
 */
public class JedisManager {

    private final static Logger log = LoggerFactory.getLogger(JedisManager.class);

    private JedisPool jedisPool;

    public Jedis getInstance() {
        Jedis jedis;
        try {
            jedis = getJedisPool().getResource();
        } catch (JedisConnectionException e) {
            String message = StringUtils.trim(e.getMessage());
            if ("Could not get a resource from the pool".equalsIgnoreCase(message)) {
                log.error("Redis服务加载失败！需要检查服务安装与否、Redis服务器开启与否、访问权限以及基本配置！");
                System.exit(0);
            }
            throw new JedisConnectionException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return jedis;
    }

    public void returnResource(Jedis jedis) {
        if (jedis == null)
            return;
        jedis.close();
    }

    public byte[] getValueByKey(int dbIndex, byte[] key) throws Exception {
        Jedis jedis = null;
        byte[] result;
        try {
            jedis = getInstance();
            jedis.select(dbIndex);
            result = jedis.get(key);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    public void deleteByKey(int dbIndex, byte[] key) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getInstance();
            jedis.select(dbIndex);
            Long result = jedis.del(key);
            ShiroLogUtils.fmtDebug(getClass(), "删除Session结果：%s", result);
        } finally {
            returnResource(jedis);
        }
    }

    public void saveValueByKey(int dbIndex, byte[] key, byte[] value, int expireTime) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getInstance();
            jedis.select(dbIndex);
            jedis.set(key, value);
            if (expireTime > 0) jedis.expire(key, expireTime);
        } finally {
            returnResource(jedis);
        }
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 从缓存中获取所有Session
     *
     * @param dbIndex           指定的数据库
     * @param redisShiroSession redisShiroSession命名空间
     * @return 所有Session
     * @throws Exception Exception
     */
    @SuppressWarnings("unchecked")
    public Collection<Session> AllSession(int dbIndex, String redisShiroSession) throws Exception {
        Jedis jedis = null;
        Set<Session> sessions = new HashSet<Session>();
        try {
            jedis = getInstance();
            jedis.select(dbIndex);

            Set<byte[]> byteKeys = jedis.keys((JedisShiroSessionRepository.REDIS_SHIRO_ALL).getBytes());
            if (byteKeys != null && byteKeys.size() > 0) {
                for (byte[] bs : byteKeys) {
                    Session obj = SerializeUtils.deserialize(jedis.get(bs),
                            Session.class);
                    if (obj != null) {
                        sessions.add(obj);
                    }
                }
            }
        } finally {
            returnResource(jedis);
        }
        return sessions;
    }
}
