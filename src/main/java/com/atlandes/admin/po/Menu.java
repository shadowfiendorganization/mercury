package com.atlandes.admin.po;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 系统菜单实体类
 * Created by TOSHIBA on 2017/06/04.
 */
public class Menu implements Serializable{

    private static final long SerialzationUID = 1L;

    private int id;
    private int menuId;
    private String code;
    private String name;
    private String remark;
    private String url;
    private int level;//级别
    private String parentCode;//父编号
    private int sortId;//排序
    private int isValid;//是否有效
    private int isVisible;//是否可见

    private ArrayList<Menu> childs = new ArrayList<Menu>();//子菜单列表

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public int getSortId() {
        return sortId;
    }

    public void setSortId(int sortId) {
        this.sortId = sortId;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }
}
