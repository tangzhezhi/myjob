package org.tang.myjob.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.myjob.dao.login.UserDao;
import org.tang.myjob.dto.system.UserDTO;

/**
 * Created by Administrator on 2015/3/16.
 */

@Service
public class LoginService {

    @Autowired
    private UserDao userDao;

    /**
     * 用户验证--用户登录时
     * @param dto
     * @return
     */
    public Boolean queryUserLoginIsExist(UserDTO dto) {
        Boolean result = false;
        try {
            result =  userDao.selectUserLoginIsExist(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
