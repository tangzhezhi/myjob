package org.tang.myjob.utils.redis;

/**
 * Created by Administrator on 2015/4/7.
 */

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 连接和使用redis资源的工具类
 * @author watson
 * @version 0.5
 */
public class RedisUtil {

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
                    jedis.publish(PushConstant.PushObject_Pub,value);
                }
                flag = true;
            }
            catch (JedisConnectionException e) {
                logger.error("生产Redis消息时--连接异常",e);
                if (null != jedis) {
                    this.getJedisPool().returnBrokenResource(jedis);
                    jedis = null;
                }
            }
            catch (Exception e) {
                logger.error("生产Redis消息时--处理异常",e);
                e.printStackTrace();
            }
            finally{
                if (null != jedis) {
                    this.closeConnection(jedis);
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
    public boolean consumeRedisAndPub(String key,String value) throws IOException {
        boolean flag = false;
        if (org.springframework.util.StringUtils.hasText(key)) {

            Jedis jedis = this.getConnection();
            try {
                if(jedis.sismember(key, value)){
                    jedis.srem(key, value);
                    jedis.publish(PushConstant.PushObject_Pub,jedis.get(key));
                }
                flag = true;
            }
            catch (JedisConnectionException e) {
                logger.error("消费Redis消息时--连接异常",e);
                if (null != jedis) {
                    this.getJedisPool().returnBrokenResource(jedis);
                    jedis = null;
                }
            }
            catch (Exception e) {
                logger.error("消费Redis消息时--处理异常",e);
                e.printStackTrace();
            }
            finally{
                if (null != jedis) {
                    this.closeConnection(jedis);
                    jedis = null;
                }

            }
        }
        return flag;
    }


}