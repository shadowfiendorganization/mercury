package com.atlandes.auth.bo;

/**
 * Created by XD.Wang on 2017/5/26.
 * 角色
 */
public class RoleBO {

    private Long id;

    private String name;

    private Integer type;

    private Long marker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getMarker() {
        return marker;
    }

    public void setMarker(Long marker) {
        this.marker = marker;
    }
}
