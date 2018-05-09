package io.lance.gradle.common.shiro.realm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author lance.
 * @time: 2018-04-27 14:25
 * @desc: 权限安全认证和授权
 */
public class UserAuthorizingRealm extends AuthorizingRealm {

    private static final Logger logger = LogManager.getLogger();


    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用,负责在应用程序中决定用户的访问控制的方法
     *
     * @author lance
     * @date 2018-04-27 14:32:15
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {

        String username = (String) pc.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(null);
        authorizationInfo.setStringPermissions(null);
        System.out.println("Shiro授权");
        return authorizationInfo;
    }

    /**
     * 登录信息和用户验证信息验证
     *
     * @author lance
     * @date 2018-04-27 14:27:52
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();                //得到用户名
        String password = new String((char[]) token.getCredentials());  //得到密码

        //TODO 验证账号的合法性
        if (true) {
            return new SimpleAuthenticationInfo(username, password, getName());
        }


        return null;
    }
}
