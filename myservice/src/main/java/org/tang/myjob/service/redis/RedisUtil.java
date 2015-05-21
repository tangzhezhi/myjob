package org.tang.myjob.service.redis;

/**
 * Created by Administrator on 2015/4/7.
 */

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.tang.myjob.utils.json.JacksonUtil;
import org.tang.myjob.utils.redis.PushConstant;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.io.IOException;
import java.util.*;

/**
 * 连接和使用redis资源的工具类
 * @author watson
 * @version 0.5
 */
@Component
public class RedisUtil {


    private static final int OVERDATETIME = 30 * 60;
    private static final int BROADCAST_OVERDATETIME = 70;//Ajax每60秒发起一次，超过BROADCAST_OVERDATETIME时间长度未发起表示已经离开该页面

    private static Logger logger = Logger.getLogger(RedisUtil.class.getName());
    /**
     * 数据源
     */
    private JedisPool jedisPool;

    /**
     * 获取数据库连接
     * @return conn
     */
    public Jedis getConnection() {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jedis;
    }

    /**
     * 关闭数据库连接
     * @param
     */
    public void closeConnection(Jedis jedis) {
        if (null != jedis) {
            try {
                jedisPool.returnResource(jedis);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 设置连接池
     * @param
     */
    public void setJedisPool(JedisPool JedisPool) {
        this.jedisPool = JedisPool;
    }

    /**
     * 获取连接池
     * @return 数据源
     */
    public JedisPool getJedisPool() {
        return jedisPool;
    }


    /**
     * 保存到redis
     * @return
     */
    public boolean productRedis(String key,String value) throws IOException {
        boolean flag = false;
        if (org.springframework.util.StringUtils.hasText(key)) {

            Jedis jedis = this.getConnection();
            try {
                if(!jedis.sismember(key, value)){
                    jedis.sadd(key, value);
                    Set set = jedis.smembers(key);
                }
                flag = true;
            }
            catch (JedisConnectionException e) {
                logger.error("生产Redis消息时--连接异常",e);
                if (null != jedis) {
                    getJedisPool().returnBrokenResource(jedis);
                    jedis = null;
                }
            }
            catch (Exception e) {
                logger.error("生产Redis消息时--处理异常",e);
                e.printStackTrace();
            }
            finally{
                if (null != jedis) {
                    closeConnection(jedis);
                    jedis = null;
                }

            }
        }
        return flag;
    }




    /**
     * 保存到redis 并且进行消息发布
     * @return
     */
    public boolean productRedisAndPub(String key,String value) throws IOException {
        boolean flag = false;
        if (org.springframework.util.StringUtils.hasText(key)) {

            Jedis jedis = this.getConnection();
            try {
                if(!jedis.sismember(key, value)){
                    jedis.sadd(key, value);
                    Set set = jedis.smembers(key);
                    JacksonUtil jacksonUtil = new JacksonUtil();
                    jedis.publish(PushConstant.PushObject_Pub,jacksonUtil.toJSon(set));
                }
                flag = true;
            }
            catch (JedisConnectionException e) {
                logger.error("生产Redis消息时--连接异常",e);
                if (null != jedis) {
                    getJedisPool().returnBrokenResource(jedis);
                    jedis = null;
                }
            }
            catch (Exception e) {
                logger.error("生产Redis消息时--处理异常",e);
                e.printStackTrace();
            }
            finally{
                if (null != jedis) {
                    closeConnection(jedis);
                    jedis = null;
                }

            }
        }
        return flag;
    }


    /**
     * 消费了消息后到redis 并且进行消息发布
     * @return
     */
    public boolean consumeRedisAndPub(String key,String value ) throws IOException {
        boolean flag = false;
        if (org.springframework.util.StringUtils.hasText(key)) {

            Jedis jedis = this.getConnection();
            try {
                if(jedis.sismember(key, value)){
                    jedis.srem(key, value);
                    Set set = jedis.smembers(key);
                    JacksonUtil jacksonUtil = new JacksonUtil();
                    jedis.publish(PushConstant.PushObject_Pub,jacksonUtil.toJSon(set));
                }
                flag = true;
            }
            catch (JedisConnectionException e) {
                logger.error("消费Redis消息时--连接异常",e);
                if (null != jedis) {
                    getJedisPool().returnBrokenResource(jedis);
                    jedis = null;
                }
            }
            catch (Exception e) {
                logger.error("消费Redis消息时--处理异常",e);
                e.printStackTrace();
            }
            finally{
                if (null != jedis) {
                    closeConnection(jedis);
                    jedis = null;
                }

            }
        }
        return flag;
    }


    /**
     * 消费了消息后到redis
     * @return
     */
    public boolean consumeRedis(String key,String value ) throws IOException {
        boolean flag = false;
        if (org.springframework.util.StringUtils.hasText(key)) {

            Jedis jedis = this.getConnection();
            try {
                if(jedis.sismember(key, value)){
                    jedis.srem(key, value);
                    Set set = jedis.smembers(key);
                }
                flag = true;
            }
            catch (JedisConnectionException e) {
                logger.error("消费Redis消息时--连接异常",e);
                if (null != jedis) {
                    getJedisPool().returnBrokenResource(jedis);
                    jedis = null;
                }
            }
            catch (Exception e) {
                logger.error("消费Redis消息时--处理异常",e);
                e.printStackTrace();
            }
            finally{
                if (null != jedis) {
                    closeConnection(jedis);
                    jedis = null;
                }

            }
        }
        return flag;
    }


    public boolean delKey(String key) throws IOException {
        boolean flag = false;
        if (org.springframework.util.StringUtils.hasText(key)) {
            Jedis jedis = this.getConnection();
            try {
                jedis.del(key);
            } catch (JedisConnectionException e) {
                logger.error("删除时异常key:" + key, e);
                if (null != jedis) {
                    getJedisPool().returnBrokenResource(jedis);
                    jedis = null;
                }
            } finally {
                if (null != jedis) {
                    closeConnection(jedis);
                    jedis = null;
                }
            }
        }
        return  flag;
    }


    public void returnResource(Jedis jedis, boolean isBroken) {
        if (jedis == null)
            return;
        if (isBroken)
            getJedisPool().returnBrokenResource(jedis);
        else
            getJedisPool().returnResource(jedis);
    }

    public byte[] getValueByKey(int dbIndex, byte[] key) throws Exception {
        Jedis jedis = null;
        byte[] result = null;
        boolean isBroken = false;
        try {
            jedis = getConnection();
            jedis.select(dbIndex);
            result = jedis.get(key);
        } catch (Exception e) {
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis, isBroken);
        }
        return result;
    }

    public void deleteByKey(int dbIndex, byte[] key) throws Exception {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = getConnection();
            jedis.select(dbIndex);
            jedis.del(key);
        } catch (Exception e) {
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis, isBroken);
        }
    }

    public void saveValueByKey(int dbIndex, byte[] key, byte[] value, int expireTime)
            throws Exception {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = getConnection();
            jedis.select(dbIndex);
            jedis.set(key, value);
            if (expireTime > 0)
                jedis.expire(key, expireTime);
        } catch (Exception e) {
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis, isBroken);
        }
    }


}