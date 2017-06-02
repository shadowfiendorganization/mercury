package com.atlandes.auth.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;

/**
 * Created by XD.Wang on 2017/5/27.
 * 缓存管理器
 */
public class MercuryCacheManager implements CacheManager, Destroyable {

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return null;
    }

    @Override
    public void destroy() throws Exception {

    }

}
