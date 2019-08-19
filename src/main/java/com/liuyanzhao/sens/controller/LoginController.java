package com.liuyanzhao.sens.controller;

import com.liuyanzhao.sens.annotation.SystemLog;
import com.liuyanzhao.sens.enums.LogType;
import com.liuyanzhao.sens.result.Result;
import com.liuyanzhao.sens.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 言曌
 * @date 2019-07-22 14:07
 */
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 登录功能
     * 验证用户名和密码，登录成功，生成token，存入到redis中
     * 登录成功
     *
     * @param response
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/doLogin")
    @SystemLog(description = "用户登录", type = LogType.LOGIN)
    public Result<String> doLogin(HttpServletResponse response,
                                  @RequestParam("username") String username,
                                  @RequestParam("password") String password) {
        //用户登录逻辑，返回token
        String token = userService.login(response, username, password);
        return Result.success(token);
    }


}
