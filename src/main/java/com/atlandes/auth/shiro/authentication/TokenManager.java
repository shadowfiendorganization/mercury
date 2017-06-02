package com.atlandes.auth.shiro.authentication;

import com.atlandes.auth.po.User;
import com.atlandes.auth.shiro.component.MercuryRealm;
import com.atlandes.auth.shiro.component.MercurySessionManager;
import com.atlandes.auth.shiro.util.ApplicationContextHolder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;

import java.util.List;

/**
 * 用户信息、Token管理
 * Created by XD.Wang on 2017/5/31.
 */
public class TokenManager {

    //用户登录管理
    private static final MercuryRealm realm = ApplicationContextHolder.getBean("mercuryRealm", MercuryRealm.class);

    //用户session管理
    private static final MercurySessionManager mercurySessionManager = ApplicationContextHolder.getBean("mercurySessionManager", MercurySessionManager.class);

    /**
     * 获取当前登录的用户User对象
     *
     * @return User
     */
    public static User getToken() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 获取当前用户的Session
     *
     * @return Session
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 获取当前用户NAME
     *
     * @return NAME
     */
    public static String getNickname() {
        return getToken().getNickName();
    }

    /**
     * 获取当前用户ID
     *
     * @return ID
     */
    public static Long getUserId() {
        return getToken() == null ? null : getToken().getId();
    }

    /**
     * 把值放入到当前登录用户的Session里
     *
     * @param key   键
     * @param value 值
     */
    public static void setVal2Session(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    /**
     * 从当前登录用户的Session里取值
     *
     * @param key 键
     * @return 值
     */
    public static Object getVal2Session(Object key) {
        return getSession().getAttribute(key);
    }

    /**
     * 获取验证码，获取一次后删除
     *
     * @return 验证码
     */
    public static String getYZM() {
        String code = (String) getSession().getAttribute("CODE");
        getSession().removeAttribute("CODE");
        return code;
    }

    /**
     * 登录
     *
     * @param user       用户名
     * @param rememberMe 是否保留登录状态
     * @return 登录的用户
     */
    public static User login(User user, Boolean rememberMe) {
        ShiroToken token = new ShiroToken(user.getEmail(), user.getPassword());
        token.setRememberMe(rememberMe);
        SecurityUtils.getSubject().login(token);
        return getToken();
    }

    /**
     * 判断是否登录
     *
     * @return 是否登录
     */
    public static boolean isLogin() {
        return null != SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 退出登录
     */
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    /**
     * 清空当前用户权限信息。
     * 目的：为了在判断权限的时候，再次会再次 <code>doGetAuthorizationInfo(...)  </code>方法。
     * ps：	当然你可以手动调用  <code> doGetAuthorizationInfo(...)  </code>方法。
     * 这里只是说明下这个逻辑，当你清空了权限，<code> doGetAuthorizationInfo(...)  </code>就会被再次调用。
     */
    public static void clearNowUserAuth() {
        realm.clearCachedAuthorizationInfo();
    }

    // 根据UserIds 	清空权限信息。
    public static void clearUserAuthByUserId(Long... userIds) {

        if (null == userIds || userIds.length == 0) return;
        List<SimplePrincipalCollection> result = mercurySessionManager.getSimplePrincipalCollectionByUserId(userIds);

        for (SimplePrincipalCollection simplePrincipalCollection : result) {
            realm.clearCachedAuthorizationInfo(simplePrincipalCollection);
        }
    }

    public static void clearUserAuthByUserId(List<Long> userIds) {
        if (null == userIds || userIds.size() == 0) {
            return;
        }
        clearUserAuthByUserId(userIds.toArray(new Long[0]));
    }
}
