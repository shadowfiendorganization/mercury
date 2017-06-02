package com.atlandes.auth.po;

/**
 * Created by XD.Wang on 2017/5/25.
 * 用户角色关系
 */
public class UserRoleRelations {

    private Long id;

    private Long uid;

    private Long rid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }
}
