package com.atlandes.auth.shiro.filter;

import com.atlandes.auth.po.User;
import com.atlandes.auth.shiro.authentication.TokenManager;
import com.atlandes.auth.shiro.util.ShiroFilterUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by XD.Wang on 2017/6/2.
 * 登录校验
 */
public class LoginFilter extends AccessControlFilter {

    final static Class<LoginFilter> CLAZZ = LoginFilter.class;

    @Override
    protected boolean isAccessAllowed(ServletRequest request,
                                      ServletResponse response, Object mappedValue) throws Exception {
        // 验证登录
        User token = TokenManager.getToken();
        if (null != token || isLoginRequest(request, response)) {
            return Boolean.TRUE;
        }
        if (ShiroFilterUtils.isAjax(request)) {
            ShiroFilterUtils.refuseAjax(response);
        }
        return Boolean.FALSE;

    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
            throws Exception {
        // 保存Request和Response 到登录后的链接
        saveRequestAndRedirectToLogin(request, response);
        return Boolean.FALSE;
    }

}
