package com.atlandes.auth.shiro.cache;

import org.apache.shiro.cache.Cache;

/**
 * Created by XD.Wang on 2017/5/28.
 * 可能不会实现
 */
public interface ShiroCacheManager {

    <K, V> Cache<K, V> getCache(String name);

    void destroy();

}
