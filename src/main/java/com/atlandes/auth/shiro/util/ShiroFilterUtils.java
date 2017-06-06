package com.atlandes.auth.shiro.util;

import com.alibaba.fastjson.JSON;
import com.atlandes.common.constant.HttpCode;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by XD.Wang on 2017/5/28.
 * 拦截器工具类
 */
public class ShiroFilterUtils {

    public final static Class<? extends ShiroFilterUtils> CLAZZ = ShiroFilterUtils.class;

    //登录页面
    public static final String LOGIN_URL = "/u/login.shtml";
    //踢出登录提示
    public final static String KICKED_OUT = "/open/kickedOut.shtml";
    //没有权限提醒
    public final static String UNAUTHORIZED = "/open/unauthorized.shtml";

    /**
     * 是否是Ajax请求
     *
     * @param request 请求
     * @return 判断结果
     */
    public static boolean isAjax(ServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"));
    }

    /**
     * response 输出JSON
     *
     * @param response  response
     * @param resultMap 结果
     * @throws IOException IO异常
     */
    public static void out(ServletResponse response, Map<String, String> resultMap) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            out.println(JSON.toJSONString(resultMap));
        } catch (Exception e) {
            ShiroLogUtils.fmtError(CLAZZ, e, "JSON即系失败。");
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
    }

    // 拒绝登录
    public static void refuseAjax(ServletResponse response) {
        Map<String, String> resultMap = new HashMap<String, String>();
        ShiroLogUtils.debug(ShiroFilterUtils.CLAZZ, "当前用户没有登录，并且是Ajax请求！");
        resultMap.put("login_status", HttpCode.AUTHENTICATION_REFUSED);
        resultMap.put("message", "\u5F53\u524D\u7528\u6237\u6CA1\u6709\u767B\u5F55\uFF01");
        ShiroFilterUtils.out(response, resultMap);
    }
}
