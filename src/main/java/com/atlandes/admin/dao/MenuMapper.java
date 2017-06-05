package com.atlandes.admin.dao;

import com.atlandes.admin.po.Menu;

import java.util.List;



/**
 * 系统菜单管理dao接口
 * Created by liucong on 2017/06/04.
 */
public interface MenuMapper {
    List<Menu> getMenuList();
    Menu selectMenuById(int id);
    int addMenu();
    int deleteMenu(int id);
    int updateMenu(Menu menu);

}
