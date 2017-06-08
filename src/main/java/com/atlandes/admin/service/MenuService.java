package com.atlandes.admin.service;

import com.atlandes.admin.dao.MenuMapper;
import com.atlandes.admin.po.Menu;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统菜单管理service层
 * Created by liucong on 2017/06/04.
 */
@Service
public class MenuService {

    @Resource
    MenuMapper menuMapper;

    private static Logger log = Logger.getLogger(MenuService.class);

    public List<Menu> getMenuList(){
        return menuMapper.getMenuList();
    }

    public Menu selectMenuById(int id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    public int addMenu(Menu menu){
        return menuMapper.insert(menu);
    }

    public int deleteMenu(int id){

        return menuMapper.deleteByPrimaryKey(id);
    }

    public int updateMenu(Menu menu){
        return menuMapper.updateByPrimaryKey(menu);
    };
}
