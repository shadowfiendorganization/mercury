package com.atlandes.auth.bo;

/**
 * Created by XD.Wang on 2017/5/26.
 * 用户权限分配情况
 */
public class PermissionAllocationBO {

    private Long id;

    private String permissionNames;

    private String permissionIds;

    private Integer type;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissionNames() {
        return permissionNames;
    }

    public void setPermissionNames(String permissionNames) {
        this.permissionNames = permissionNames;
    }

    public String getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(String permissionIds) {
        this.permissionIds = permissionIds;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
