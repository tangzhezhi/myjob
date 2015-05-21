package org.tang.myjob.controller.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.tang.myjob.service.redis.RedisUtil;

/**
 * 这里的name是指自定义relm中的授权/认证的类名加上授权/认证英文名字
 *
 * @author michael
 */
public class JedisShiroCacheManager implements ShiroCacheManager {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public <K, V> Cache<K, V> getCache(String name) {
        return new JedisShiroCache<K, V>(name, redisUtil);
    }

    @Override
    public void destroy() {
        redisUtil.getConnection().shutdown();
    }

    public RedisUtil getRedisUtil() {
        return redisUtil;
    }

    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }
}
