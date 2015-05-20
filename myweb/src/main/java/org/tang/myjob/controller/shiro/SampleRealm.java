package org.tang.myjob.controller.shiro;
    import java.util.Set;
    import org.apache.commons.lang.StringUtils;
    import org.apache.shiro.authc.AccountException;
    import org.apache.shiro.authc.AuthenticationException;
    import org.apache.shiro.authc.AuthenticationInfo;
    import org.apache.shiro.authc.AuthenticationToken;
    import org.apache.shiro.authc.DisabledAccountException;
    import org.apache.shiro.authc.SimpleAuthenticationInfo;
    import org.apache.shiro.authc.UnknownAccountException;
    import org.apache.shiro.authc.UsernamePasswordToken;
    import org.apache.shiro.authz.AuthorizationInfo;
    import org.apache.shiro.authz.SimpleAuthorizationInfo;
    import org.apache.shiro.realm.AuthorizingRealm;
    import org.apache.shiro.subject.PrincipalCollection;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.tang.myjob.dto.system.RoleDTO;
    import org.tang.myjob.dto.system.UserDTO;
    import org.tang.myjob.service.system.UserService;

/**
 * @author Dylan
 * @time 2013-8-2
 */
public class SampleRealm extends AuthorizingRealm {

    private final static Logger LOG = LoggerFactory.getLogger(SampleRealm.class);

    public final static String REALM_NAME = "SampleRealm";

    @Autowired
    private UserService userService;

    public SampleRealm() {
        setName(REALM_NAME); // This name must match the name in the User
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = token.getUsername();
        if(LOG.isTraceEnabled()){
            LOG.trace("开始认证 "+ username);
        }
        try {
            if(StringUtils.isBlank(username)){
                throw new AccountException("can not handle this login");
            }
            UserDTO user = userService.getByUsername(username);
            checkUser(user, username);
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
        } catch (Exception e) {
            throw translateAuthenticationException(e);
        }
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String username = (String)getAvailablePrincipal(principals);

        if(LOG.isTraceEnabled()){
            LOG.trace("开始授权 "+ username);
        }

        UserDTO user = userService.getByUsername(username);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> rolesAsString = user.getRolesAsString();
        info.addRoles(rolesAsString);
        if(user.hasPermissions()){
            info.addStringPermissions(user.getPermissionsAsString());
        }
        for(RoleDTO role : user.getRoles()){
            info.addStringPermissions(role.getsPermissionsAsString());
        }
        return info;
    }

    /**
     * 异常转换
     * @param e
     * @return
     */
    private AuthenticationException translateAuthenticationException(Exception e) {
        if (e instanceof AuthenticationException) {
            return (AuthenticationException) e;
        }
        if(e instanceof DisabledAccountException){
            return (DisabledAccountException)e;
        }
        if(e instanceof UnknownAccountException){
            return (UnknownAccountException)e;
        }
        return new AuthenticationException(e);
    }
    /**
     * 检查用户
     * @param user
     * @param username
     */
    private void checkUser(UserDTO user,String username){
        if(null == user){
            throw new UnknownAccountException(username + " can not find "+username+" from system");
        }
        if(user.getIsLocked()>0){
            throw new DisabledAccountException("the account is locked now");
        }
    }

}

