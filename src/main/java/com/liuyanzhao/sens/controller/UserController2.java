package com.liuyanzhao.sens.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.liuyanzhao.sens.entity.User;
import com.liuyanzhao.sens.service.UserService2;
import com.liuyanzhao.sens.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * 后台用户管理控制器
 * MyBatisPlus 版本
 */
@Slf4j
@RestController
@RequestMapping(value = "/user2")
public class UserController2 {

    @Autowired
    private UserService2 userService2;


    /**
     * 获得用户列表
     *
     * @param page  页码
     * @param size  页大小
     * @param model model
     * @return JSON格式数据
     */
    @GetMapping
    public Response<Page<User>> users(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                      @RequestParam(value = "size", defaultValue = "10") Integer size, Model model) {
        //用户列表
        Page pageable = new Page(page, size);
        Page<User> users = userService2.findAll(pageable);
        return Response.yes(users);
    }

    /**
     * 添加用户
     *
     * @param user 用户
     * @return JSON格式数据
     */
    @PostMapping
    public Response add(@RequestBody User user) {
        try {
            userService2.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.no();
        }
        return Response.yes();
    }

    /**
     * 删除用户
     *
     * @param user 用户
     * @return JSON格式数据
     */
    @PutMapping
    public Response update(@RequestBody User user) {
        try {
            userService2.update(user);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.no();
        }
        return Response.yes();
    }

    /**
     * 删除用户
     *
     * @param id ID
     * @return JSON格式数据
     */
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Long id) {
        try {
            userService2.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.no();
        }
        return Response.yes();
    }

    /**
     * 根据ID查询用户
     *
     * @param id ID
     * @return JSON格式数据
     */
    @GetMapping("/{id}")
    public Response get(@PathVariable("id") Long id) {
        User user = null;
        try {
            user = userService2.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.no();
        }
        return Response.yes(user);
    }


}
