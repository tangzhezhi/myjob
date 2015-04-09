package org.tang.myjob.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.myjob.dao.login.UserDao;
import org.tang.myjob.dto.system.UserDTO;
import org.tang.myjob.service.redis.RedisUtil;
import org.tang.myjob.utils.json.JacksonUtil;

/**
 * Created by Administrator on 2015/3/16.
 */

@Service
public class LoginService {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserDao userDao;

    /**
     * 用户验证--用户登录时
     * @param dto
     * @return
     */
    public Boolean queryUserLoginIsExist(UserDTO dto) {
        Boolean result = false;
        int flag = 0;

        try {
            flag =  userDao.selectUserLoginIsExist(dto);
            if(flag > 0){
                JacksonUtil jacksonUtil = new JacksonUtil();
                String jsonStr = jacksonUtil.toJSon(dto);
                redisUtil.productRedisAndPub("user:online",jsonStr);
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
