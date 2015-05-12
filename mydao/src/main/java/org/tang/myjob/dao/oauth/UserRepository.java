package org.tang.myjob.dao.oauth;


import org.springframework.stereotype.Repository;
import org.tang.myjob.dto.oauth.User;

/**
 * @author Shengzhao Li
 */

@Repository
public interface UserRepository {

    User findByGuid(String guid);

    void saveUser(User user);

    void updateUser(User user);

    User findByUsername(String username);

}