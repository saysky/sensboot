package com.liuyanzhao.sens.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.liuyanzhao.sens.annotation.SystemLog;
import com.liuyanzhao.sens.entity.User;
import com.liuyanzhao.sens.enums.LogType;
import com.liuyanzhao.sens.result.Result;
import com.liuyanzhao.sens.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * 后台用户管理控制器
 * 异常不用捕获，用统一异常捕获处理
 *
 * @author liuyanzhao
 */
@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 当前登录用户
     *
     * @param user 用户信息，由封装注入
     * @return
     */
    @RequestMapping("/current")
    @ResponseBody
    public Result<User> current(User user) {
        return Result.success(user);
    }

    /**
     * 获得用户列表
     *
     * @param page  页码
     * @param size  页大小
     * @param model model
     * @return JSON格式数据
     */
    @GetMapping
    @SystemLog(description = "查询用户列表", type = LogType.OPERATION)
    public Result<Page<User>> users(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                    @RequestParam(value = "size", defaultValue = "10") Integer size, Model model) {
        //用户列表
        Page pageable = new Page(page, size);
        Page<User> users = userService.findAll(pageable);
        return Result.success(users);
    }

    /**
     * 添加用户
     *
     * @param user 用户
     * @return JSON格式数据
     */
    @PostMapping
    public Result<Boolean> add(@RequestBody User user) {
        userService.insert(user);
        return Result.success(true);
    }

    /**
     * 更新用户
     *
     * @param user 用户
     * @return JSON格式数据
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody User user) {
        userService.update(user);
        return Result.success(true);
    }

    /**
     * 删除用户
     *
     * @param id ID
     * @return JSON格式数据
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return Result.success(true);
    }

    /**
     * 根据ID查询用户
     *
     * @param id ID
     * @return JSON格式数据
     */
    @GetMapping("/{id}")
    public Result<User> get(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        return Result.success(user);
    }


}
