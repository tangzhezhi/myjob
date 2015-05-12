package org.tang.myjob.service.business;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.tang.myjob.dao.oauth.UserRepository;
import org.tang.myjob.dto.oauth.User;
import org.tang.myjob.dto.security.WdcyUserDetails;

/**
 * @author Shengzhao Li
 */
@Service("userService")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Not found any user for username[" + username + "]");
        }

        return new WdcyUserDetails(user);
    }
}