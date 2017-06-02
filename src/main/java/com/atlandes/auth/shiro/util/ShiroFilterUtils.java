package com.atlandes.auth.shiro.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by XD.Wang on 2017/5/28.
 * 拦截器工具类
 */
public class ShiroFilterUtils {

    public final static Class<? extends ShiroFilterUtils> CLAZZ = ShiroFilterUtils.class;

    //登录页面
    static final String LOGIN_URL = "/u/login.shtml";
    //踢出登录提示
    final static String KICKED_OUT = "/open/kickedOut.shtml";
    //没有权限提醒
    final static String UNAUTHORIZED = "/open/unauthorized.shtml";

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
}
