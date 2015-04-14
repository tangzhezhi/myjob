package org.tang.myjob.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.myjob.dao.login.UserDao;
import org.tang.myjob.dto.system.UserDTO;
import org.tang.myjob.service.redis.RedisUtil;
import org.tang.myjob.utils.json.JacksonUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.*;

/**
 * Created by Administrator on 2015/3/16.
 */

@Service
public class LoginService {

    private static final String SID_PREFIX = "online:sid:";
    private static final String UID_PREFIX = "online:uid:";
    private static final String VID_PREFIX = "online:vid:";
    private static final int OVERDATETIME = 30 * 60;
    private static final int BROADCAST_OVERDATETIME = 70;//Ajax每60秒发起一次，超过BROADCAST_OVERDATETIME时间长度未发起表示已经离开该页面
    private JacksonUtil jacksonUtil = new JacksonUtil();

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserDao userDao;


    public  void login(String sid,UserDTO user){
        Jedis jedis = redisUtil.getConnection();
        jedis.setex(SID_PREFIX+sid, OVERDATETIME,  jacksonUtil.toJSon(user));   //根据sessionid得到用户信息
        jedis.setex(UID_PREFIX+user.getUserName(), OVERDATETIME, sid); //根据userId得到sessionid
        redisUtil.closeConnection(jedis);
    }


    /**
     *
     * @Title: logout
     * @Description: 退出
     * @param @param UserId  使指定用户下线
     * @return void
     * @throws
     */
    public  void logout(String uid){
        Jedis jedis = redisUtil.getConnection();

        //根据sessionid 删除session
        jedis.del(jedis.get(UID_PREFIX+uid));

        //删除sid
        jedis.del(SID_PREFIX+jedis.get(UID_PREFIX+uid));

        //删除uid
        jedis.del(UID_PREFIX+uid);

        redisUtil.closeConnection(jedis);
    }


    /**
     *
     * @Title: logout
     * @Description: 退出
     * @param @param sessionId
     * @return void
     * @throws
     */
    public  void logout(String sid,String uid){
        Jedis jedis = redisUtil.getConnection();
        jedis.del(SID_PREFIX+sid);
        jedis.del(UID_PREFIX+uid);
        redisUtil.closeConnection(jedis);
    }


    /**
     *
     * @Title: isOnline
     * @Description: 指定账号是否登陆
     * @param @param sessionId
     * @param @return
     * @return boolean
     * @throws
     */
    public  boolean isOnline(String uid){
        Jedis jedis = redisUtil.getConnection();
        boolean isLogin = jedis.exists(UID_PREFIX+uid);
        redisUtil.closeConnection(jedis);
        return isLogin;
    }


    public  boolean isOnline(String uid,String sid){
        Jedis jedis = redisUtil.getConnection();
        String loginSid = jedis.get(UID_PREFIX+uid);
        redisUtil.closeConnection(jedis);
        return sid.equals(loginSid);
    }


    public  String getClientUserBySessionId(String sid){
        Jedis jedis = redisUtil.getConnection();
        String user = jedis.get(SID_PREFIX+sid);
        redisUtil.closeConnection(jedis);
        return user;
    }

    public  String getClientUserByUid(String uid){
        Jedis jedis = redisUtil.getConnection();
        String user = jedis.get(SID_PREFIX+jedis.get(UID_PREFIX+uid));
        redisUtil.closeConnection(jedis);
        return user;
    }



    /**
     *
     * @Title: online
     * @Description: 所有的key
     * @return List
     * @throws
     */
    public  List online(){
        Jedis jedis = redisUtil.getConnection();
        Set online = jedis.keys(SID_PREFIX+"*");
        redisUtil.closeConnection(jedis);
        return new ArrayList(online);
    }


    private static String getKey(Object obj){
        String temp = String.valueOf(obj);
        String key[] = temp.split(":");
        return SID_PREFIX+key[key.length-1];
    }

    /**
     *
     * @Title: online
     * @Description: 分页显示在线列表
     * @return List
     * @throws
     */
    public  List onlineByPage(int page,int pageSize) throws Exception{
        Jedis jedis = redisUtil.getConnection();

        Set onlineSet = jedis.keys(SID_PREFIX+"*");


        List onlines =new ArrayList(onlineSet);

        if(onlines.size() == 0){
            return null;
        }

        Pipeline pip = jedis.pipelined();
        for(Object key:onlines){
            pip.get(getKey(key));
        }
        List result = pip.syncAndReturnAll();
        redisUtil.closeConnection(jedis);

        List<UserDTO> listUser=new ArrayList<UserDTO>();
        for(int i=0;i<result.size();i++){


            listUser.add(jacksonUtil.readValue((String) result.get(i), UserDTO.class));
        }
        Collections.sort(listUser, new Comparator<UserDTO>() {
            public int compare(UserDTO o1, UserDTO o2) {
                return o2.getLastLoginTime().compareTo(o1.getLastLoginTime());
            }
        });
        onlines=listUser;
        int start = (page - 1) * pageSize;
        int toIndex=(start+pageSize)>onlines.size()?onlines.size():start+pageSize;
        List list = onlines.subList(start, toIndex);

        return list;
    }




    /**
     * 用户验证--用户登录时
     * @param dto
     * @return
     */
    public UserDTO queryUser(UserDTO dto) {
        Boolean result = false;
        UserDTO u = null;

        try {
            u =  userDao.selectUser(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

}
