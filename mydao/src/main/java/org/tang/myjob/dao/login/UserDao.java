package org.tang.myjob.dao.login;

import org.springframework.stereotype.Repository;
import org.tang.myjob.dto.system.UserDTO;

/**
 * Created by Administrator on 2015/3/17.
 */

@Repository
public interface UserDao {

    public Integer selectUserLoginIsExist(UserDTO dto);
}
