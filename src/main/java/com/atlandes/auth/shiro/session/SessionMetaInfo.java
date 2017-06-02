package com.atlandes.auth.shiro.session;

import java.io.Serializable;

/**
 * Created by XD.Wang on 2017/5/31.
 * Session元信息
 */
public class SessionMetaInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    //  是否在线 true：在线；false：下线
    private Boolean onlineStatus = Boolean.TRUE;

    public Boolean isOnlineStatus() {
        return onlineStatus;
    }

    public Boolean getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Boolean onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

}
