package com.liuyanzhao.sens.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.liuyanzhao.sens.entity.User;
import com.liuyanzhao.sens.service.UserService;
import com.liuyanzhao.sens.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * 后台用户管理控制器
 */
@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

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
        Page<User> users = userService.findAll(pageable);
        return Response.yes(users);
    }


}
