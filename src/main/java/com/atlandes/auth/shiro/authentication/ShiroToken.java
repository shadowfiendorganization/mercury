package com.atlandes.auth.shiro.authentication;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 认证信息
 * Created by XD.Wang on 2017/5/31.
 */
public class ShiroToken extends UsernamePasswordToken implements java.io.Serializable {

    private static final long serialVersionUID = -6451794657814516274L;

    public ShiroToken(String username, String pswd) {
        super(username, pswd);
        this.pswd = pswd;
    }

    private String pswd;

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

}
