package com.atlandes.admin.controller;

import com.alibaba.fastjson.JSON;
import com.atlandes.admin.po.Menu;
import com.atlandes.admin.service.MenuService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统菜单管理Controller层
 * Created by TOSHIBA on 2017/06/04.
 */
@Controller
@RequestMapping("menu")
public class MenuController {

    @Resource
    MenuService menuService;

    private static Logger log = Logger.getLogger(MenuController.class);

    /**
     * 菜单管理主页面
     * @return 路径
     */
    @ResponseBody
    @RequestMapping("main")
    public ModelAndView mainPage(){
        ModelAndView modelAndView = new ModelAndView("admin/menu");
       // modelAndView.addObject("name", "xxx");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("getMenuList")
    public String getMenuList(){
        List<Menu> menuList = menuService.getMenuList();
        return JSON.toJSONString(menuList);
    }

    @ResponseBody
    @RequestMapping("selectMenuById")
    public String selectMenuById(int id){
        Menu menu = menuService.selectMenuById(id);
        return JSON.toJSONString(menu);
    }

    @ResponseBody
    @RequestMapping("addMenu")
    public int addMenu(Menu menu){
        int a = menuService.addMenu(menu);
        return a;
    }

    @ResponseBody
    @RequestMapping("deleteMenu")
    public int deleteMenu(int id){
        menuService.deleteMenu(id);
        return 1;
    }

    @ResponseBody
    @RequestMapping("updateMenu")
    public int updateMenu(Menu menu){
        menuService.updateMenu(menu);
        return 1;
    }

/*    @ResponseBody
    @RequestMapping("toAdd")
    public int toAdd(HttpServletRequest request,int id){
        request.setAttribute();
        request.setAttribute();
        return "";
    }

    @ResponseBody
    @RequestMapping("toUpdate")
    public int toAdd(HttpServletRequest request,int id){
        request.setAttribute();
        request.setAttribute();
        return "";
    }*/
}
