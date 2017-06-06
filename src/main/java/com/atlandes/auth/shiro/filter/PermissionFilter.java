package com.atlandes.auth.shiro.filter;

import com.atlandes.auth.shiro.util.ShiroFilterUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by XD.Wang on 2017/6/2.
 * 权限校验
 */
public class PermissionFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request,
                                      ServletResponse response, Object mappedValue) throws Exception {
        // 先判断带参数的权限判断
        Subject subject = getSubject(request, response);
        if (null != mappedValue) {
            String[] args = (String[]) mappedValue;
            for (String permission : args) {
                if (subject.isPermitted(permission)) {
                    return Boolean.TRUE;
                }
            }
        }
        HttpServletRequest httpRequest = ((HttpServletRequest) request);
        String uri = httpRequest.getRequestURI();
//        String basePath = httpRequest.getContextPath();//获取basePath
//        if (null != uri && uri.startsWith(basePath)) {
//            uri = uri.replace(basePath, "");
//        }

        if (subject.isPermitted(uri)) {
            return Boolean.TRUE;
        }
        if (ShiroFilterUtils.isAjax(request)) {
            ShiroFilterUtils.refuseAjax(response);
        }
        return Boolean.FALSE;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request,
                                     ServletResponse response) throws Exception {

        Subject subject = getSubject(request, response);
        // 表示没有登录，重定向到登录页面
        if (null == subject.getPrincipal()) {
            saveRequest(request);
            WebUtils.issueRedirect(request, response, ShiroFilterUtils.LOGIN_URL);
        } else {
            // 如果有未授权页面跳转过去
            if (StringUtils.hasText(ShiroFilterUtils.UNAUTHORIZED)) {
                WebUtils.issueRedirect(request, response, ShiroFilterUtils.UNAUTHORIZED);
            } else {
                // 否则返回401未授权状态码
                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        return Boolean.FALSE;
    }

}
