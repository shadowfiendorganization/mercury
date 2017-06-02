package com.atlandes.auth.bo;

/**
 * Created by XD.Wang on 2017/5/26.
 * 权限
 */
public class PermissionBO {

    private Long id;

    private Long roleId;

    private String name;

    private String url;

    private Long marker;

    public Long getMarker() {
        return marker;
    }

    public void setMarker(Long marker) {
        this.marker = marker;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
