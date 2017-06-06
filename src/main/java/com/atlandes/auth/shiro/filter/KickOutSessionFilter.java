package com.atlandes.auth.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.atlandes.auth.shiro.authentication.TokenManager;
import com.atlandes.auth.shiro.session.ShiroSessionRepository;
import com.atlandes.auth.shiro.util.CacheUtils;
import com.atlandes.auth.shiro.util.ShiroFilterUtils;
import com.atlandes.auth.shiro.util.ShiroLogUtils;
import com.atlandes.common.constant.HttpCode;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by XD.Wang on 2017/5/27.
 * 踢出校验
 */
@SuppressWarnings({"unchecked", "static-access"})
public class KickOutSessionFilter extends AccessControlFilter {

    private static String KICK_OUT_URL = "/u/login.sHtml?kickOut";
    //  在线用户
    private final static String ONLINE_USER = KickOutSessionFilter.class.getCanonicalName() + "_online_user";
    //  是否踢出
    private final static String KICK_OUT_STATUS = KickOutSessionFilter.class.getCanonicalName() + "_kickOut_status";
    //  敏感资源目录
    private final static String SENSITIVE_URL = "/safe/";
    //  操作Session
    private static ShiroSessionRepository shiroSessionRepository;
    //  缓存
    private static CacheUtils cache;

    /**
     * 异步请求（ajax）返回提示信息
     * 普通请求踢回登录页
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request,
                                      ServletResponse response, Object mappedValue) throws Exception {
        //  获取主体
        HttpServletRequest httpRequest = ((HttpServletRequest) request);
        String url = httpRequest.getRequestURI();
        Subject subject = getSubject(request, response);
        //  如果是私密目录 or 未登录 踢出
        if (url.startsWith(SENSITIVE_URL) || (!subject.isAuthenticated() && !subject.isRemembered())) {
            return true;
        }
        Session session = subject.getSession();

        Boolean marker = (Boolean) session.getAttribute(KICK_OUT_STATUS);
        if (null != marker && marker) {
            Map<String, String> resultMap = new HashMap<String, String>();
            //判断是不是Ajax请求
            if (ShiroFilterUtils.isAjax(request)) {
                ShiroLogUtils.debug(getClass(), "当前用户已经在其他地方登录，with Ajax请求！");
                resultMap.put("user_status", HttpCode.AUTHENTICATION_CANCEL);
                resultMap.put("message", "您已经在其他地方登录，请重新登录！");
                out(response, resultMap);
            }
            return Boolean.FALSE;
        }

        Serializable sessionId = session.getId();

        //从缓存获取用户-Session信息 <UserId,SessionId>
        LinkedHashMap<Long, Serializable> infoMap = cache.get(ONLINE_USER, LinkedHashMap.class);
        //如果不存在，创建一个新的
        infoMap = null == infoMap ? new LinkedHashMap<Long, Serializable>() : infoMap;

        //获取tokenId
        Long userId = TokenManager.getUserId();

        //如果已经包含当前Session，并且是同一个用户，跳过。
        if (infoMap.containsKey(userId) && infoMap.containsValue(sessionId)) {
            //更新存储到缓存1个小时（这个时间最好和session的有效期一致或者大于session的有效期）
            cache.setWithExpireTime(ONLINE_USER, infoMap, 3600);
            return Boolean.TRUE;
        }

        // 如果用户相同，Session不相同，那么就要处理了 1.获取到原来的session，并且标记为踢出。 2.继续走
        if (infoMap.containsKey(userId) && !infoMap.containsValue(sessionId)) {
            Serializable oldSessionId = infoMap.get(userId);
            Session oldSession = shiroSessionRepository.getSession(oldSessionId);
            if (null != oldSession) {
                //标记session已经踢出
                oldSession.setAttribute(KICK_OUT_STATUS, Boolean.TRUE);
                shiroSessionRepository.saveSession(oldSession);//更新session
                ShiroLogUtils.fmtDebug(getClass(), "kickout old session success,oldId[%s]", oldSessionId);
            } else {
                shiroSessionRepository.deleteSession(oldSessionId);
                infoMap.remove(userId);
                //存储到缓存1个小时（这个时间最好和session的有效期一致或者大于session的有效期）
                cache.setWithExpireTime(ONLINE_USER, infoMap, 3600);
            }
            return Boolean.TRUE;
        }

        if (!infoMap.containsKey(userId) && !infoMap.containsValue(sessionId)) {
            infoMap.put(userId, sessionId);
            //存储到缓存1个小时（这个时间最好和session的有效期一致或者大于session的有效期）
            cache.setWithExpireTime(ONLINE_USER, infoMap, 3600);
        }
        return Boolean.TRUE;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request,
                                     ServletResponse response) throws Exception {

        //先退出
        Subject subject = getSubject(request, response);
        subject.logout();
        WebUtils.getSavedRequest(request);
        //再重定向
        WebUtils.issueRedirect(request, response, KICK_OUT_URL);
        return false;
    }

    private void out(ServletResponse resp, Map<String, String> resultMap)
            throws IOException {
        try {
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();
            out.println(JSON.toJSONString(resultMap));
            out.flush();
            out.close();
        } catch (Exception e) {
            // 可无视
            ShiroLogUtils.error(getClass(), "KickOutSessionFilter.class 输出JSON异常！");
        }
    }

    public static void setShiroSessionRepository(ShiroSessionRepository shiroSessionRepository) {
        KickOutSessionFilter.shiroSessionRepository = shiroSessionRepository;
    }

    public static String getKickOutUrl() {
        return KICK_OUT_URL;
    }

    public static void setKickOutUrl(String kickOutUrl) {
        KickOutSessionFilter.KICK_OUT_URL = kickOutUrl;
    }

}
