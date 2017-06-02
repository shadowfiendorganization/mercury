package com.atlandes.auth.po;

/**
 * Created by XD.Wang on 2017/5/25.
 * 角色权限关系
 */
public class RolePermissionRelations {

    private Long id;

    private Long pid;

    private Long rid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }
}
