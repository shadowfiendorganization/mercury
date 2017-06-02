package com.atlandes.auth.shiro.util;

import com.atlandes.auth.shiro.cache.JedisManager;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by XD.Wang on 2017/6/2.
 * 基于Redis的缓存工具类
 */
public class CacheUtils {

    private final static JedisManager jedisManager = ApplicationContextHolder.getBean("jedisManager", JedisManager.class);

    private CacheUtils() {
    }

    /**
     * 按键获取
     *
     * @param <T>          值的类型
     * @param key          redis键
     * @param requiredType 值的类型
     * @return redis值
     */
    public static <T> T get(String key, Class<T>... requiredType) {
        Jedis jds = null;
        try {
            jds = jedisManager.getInstance();
            jds.select(0);
            byte[] sKey = SerializeUtils.serialize(key);
            return SerializeUtils.deserialize(jds.get(sKey), requiredType);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jds);
        }
        return null;
    }

    /**
     * 存储键值对到缓存
     *
     * @param key   键
     * @param value 值
     */
    public static void set(Object key, Object value) {
        Jedis jds = null;
        try {
            jds = jedisManager.getInstance();
            jds.select(0);
            byte[] sKey = SerializeUtils.serialize(key);
            byte[] sValue = SerializeUtils.serialize(value);
            jds.set(sKey, sValue);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jds);
        }
    }

    /**
     * 带过期时间的Set
     *
     * @param key   键
     * @param value 值
     * @param timer （秒）
     */
    public static void setWithExpireTime(Object key, Object value, int timer) {
        Jedis jds = null;
        try {
            jds = jedisManager.getInstance();
            jds.select(0);
            byte[] sKey = SerializeUtils.serialize(key);
            byte[] sValue = SerializeUtils.serialize(value);
            jds.setex(sKey, timer, sValue);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jds);
        }

    }

    /**
     * 返回 key 指定的哈希集中指定字段的值
     *
     * @param <T>          类型
     * @param mapKey       map
     * @param key          map里的key
     * @param requiredType value的泛型类型
     * @return map里的值
     */
    @SuppressWarnings("unchecked")
    public static <T> T getValueByMapKey(String mapKey, String key, Class<T> requiredType) {
        Jedis jds = null;
        try {
            jds = jedisManager.getInstance();
            jds.select(0);
            byte[] mKey = SerializeUtils.serialize(mapKey);
            byte[] sKey = SerializeUtils.serialize(key);
            List<byte[]> result = jds.hmget(mKey, sKey);
            if (null != result && result.size() > 0) {
                byte[] x = result.get(0);
                return SerializeUtils.deserialize(x, requiredType);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jds);
        }
        return null;
    }

    /**
     * 设置键值对到Map中
     *
     * @param mapKey map
     * @param key    map里的key
     * @param value  map里的value
     */
    public static void setValue2Map(String mapKey, String key, Object value) {
        Jedis jds = null;
        try {
            jds = jedisManager.getInstance();
            jds.select(0);
            byte[] mKey = SerializeUtils.serialize(mapKey);
            byte[] sKey = SerializeUtils.serialize(key);
            byte[] sValue = SerializeUtils.serialize(value);
            jds.hset(mKey, sKey, sValue);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jds);
        }

    }

    /**
     * 删除Map里指定键里的值
     *
     * @param mapKey map
     * @param dKey   键
     * @return 移除成功的数量
     */
    public static Long delMapKey(String mapKey, String... dKey) {
        Jedis jds = null;
        try {
            jds = jedisManager.getInstance();
            jds.select(0);
            byte[][] dx = new byte[dKey.length][];
            for (int i = 0; i < dKey.length; i++) {
                dx[i] = SerializeUtils.serialize(dKey[i]);
            }
            byte[] mKey = SerializeUtils.serialize(mapKey);
            return jds.hdel(mKey, dx);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jds);
        }
        return 0L;
    }

    /**
     * 往redis里取set整个集合
     *
     * @param <T>          类型
     * @param setKey       set的键
     * @param requiredType 类型
     * @return set
     */
    @SuppressWarnings("unchecked")
    public static <T> Set<T> getVByList(String setKey, Class<T> requiredType) {
        Jedis jds = null;
        try {
            jds = jedisManager.getInstance();
            jds.select(0);
            byte[] lKey = SerializeUtils.serialize(setKey);
            Set<T> set = new TreeSet<T>();
            Set<byte[]> xx = jds.smembers(lKey);
            for (byte[] bs : xx) {
                T t = SerializeUtils.deserialize(bs, requiredType);
                set.add(t);
            }
            return set;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jds);
        }
        return null;
    }

    /**
     * 获取Set长度
     *
     * @param setKey set的键
     * @return 长度
     */
    public static Long getSetLength(String setKey) {
        Jedis jds = null;
        try {
            jds = jedisManager.getInstance();
            jds.select(0);
            return jds.scard(setKey);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jds);
        }
        return null;
    }

    /**
     * 删除Set
     *
     * @param dKey
     * @return 成功个数
     */
    public static Long delSetByKey(String key, String... dKey) {
        Jedis jds = null;
        try {
            jds = jedisManager.getInstance();
            jds.select(0);
            Long result;
            if (null == dKey) {
                result = jds.srem(key);
            } else {
                result = jds.del(key);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jds);
        }
        return 0L;
    }

    /**
     * 随机取 Set 中的一个值
     *
     * @param key set的key
     * @return 值
     */
    public static String srandmember(String key) {
        Jedis jds = null;
        try {
            jds = jedisManager.getInstance();
            jds.select(0);
            return jds.srandmember(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jds);
        }
        return null;
    }

    /**
     * 往redis里存Set
     *
     * @param setKey set的key
     * @param value  要放进去的值
     */
    public static void setValue2Set(String setKey, String value) {
        Jedis jds = null;
        try {
            jds = jedisManager.getInstance();
            jds.select(0);
            jds.sadd(setKey, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jds);
        }
    }

    /**
     * 取set
     *
     * @param key set的键
     * @return set
     */
    public static Set<String> getSetByKey(String key) {
        Jedis jds = null;
        try {
            jds = jedisManager.getInstance();
            jds.select(0);
            return jds.smembers(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jds);
        }
        return null;

    }


    /**
     * 往redis里存List
     *
     * @param listKey list的key
     * @param value   放进去的值
     */
    public static void setValue2List(String listKey, Object value) {
        Jedis jds = null;
        try {
            jds = jedisManager.getInstance();
            jds.select(0);
            byte[] lKey = SerializeUtils.serialize(listKey);
            byte[] sValue = SerializeUtils.serialize(value);
            jds.rpush(lKey, sValue);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jds);
        }
    }

    /**
     * 往redis里取list
     *
     * @param <T>          类型
     * @param listKey      list的键
     * @param start        开始索引
     * @param end          结束索引
     * @param requiredType 类型
     * @return list
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> getValueFromList(String listKey, int start, int end, Class<T> requiredType) {
        Jedis jds = null;
        try {
            jds = jedisManager.getInstance();
            jds.select(0);
            byte[] lKey = SerializeUtils.serialize(listKey);
            List<T> list = new ArrayList<T>();
            List<byte[]> xx = jds.lrange(lKey, start, end);
            for (byte[] bs : xx) {
                T t = SerializeUtils.deserialize(bs, requiredType);
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jds);
        }
        return null;
    }

    /**
     * 获取list长度
     *
     * @param listKey list的键
     * @return 长度
     */
    public static Long getListLength(String listKey) {
        Jedis jds = null;
        try {
            jds = jedisManager.getInstance();
            jds.select(0);
            byte[] lKey = SerializeUtils.serialize(listKey);
            return jds.llen(lKey);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jds);
        }
        return null;
    }

    /**
     * 删除
     *
     * @param dKey 要删除的KEY
     * @return 成功数
     */
    public static Long delByKey(String... dKey) {
        Jedis jds = null;
        try {
            jds = jedisManager.getInstance();
            jds.select(0);
            byte[][] dx = new byte[dKey.length][];
            for (int i = 0; i < dKey.length; i++) {
                dx[i] = SerializeUtils.serialize(dKey[i]);
            }
            return jds.del(dx);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jds);
        }
        return 0L;
    }

    /**
     * 判断是否存在
     *
     * @param existsKey 要判断额键
     * @return 是否存在
     */
    public static boolean exists(String existsKey) {
        Jedis jds = null;
        try {
            jds = jedisManager.getInstance();
            jds.select(0);
            byte[] lKey = SerializeUtils.serialize(existsKey);
            return jds.exists(lKey);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jds);
        }
        return false;
    }

    /**
     * 释放Redis资源
     *
     * @param jedis 实例
     */
    public static void returnResource(Jedis jedis) {
        if (jedis == null)
            return;
        jedis.close();
    }

}
