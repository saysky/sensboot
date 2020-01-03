package com.liuyanzhao.sens.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.liuyanzhao.sens.entity.User;
import com.liuyanzhao.sens.exception.GlobalException;
import com.liuyanzhao.sens.mapper.db1.UserMapper;
import com.liuyanzhao.sens.result.CodeMsg;
import com.liuyanzhao.sens.service.UserService;
import com.liuyanzhao.sens.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 用户业务逻辑实现类
 * MyBatis Plus 版本
 */
@Service
public class UserServiceImpl implements UserService {


    public static final String COOKIE_NAME_TOKEN = "token";

    /**
     * token过期时间，2天
     */
    public static final int TOKEN_EXPIRE = 3600 * 24 * 2;


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertTestTransactional(User user) {
        userMapper.insert(user);

        System.out.println(1 / 0);
    }

    @Override
    public void update(User user) {
        userMapper.updateById(user);
    }

    @Override
    public void deleteById(Long userId) {
        userMapper.deleteById(userId);
    }

    @Override
    public Page<User> findAll(Page<User> page) {
        return page.setRecords(userMapper.findAll(page));
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectList(null);
    }

    @Override
    public User findById(Long userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }


    @Override
    public String login(HttpServletResponse response, String username, String password) {

        //判断用户名是否存在
        User user = findByUsername(username);
        if (user == null) {
            throw new GlobalException(CodeMsg.USERNAME_NOT_EXIST);
        }

        //验证密码，这里为了例子简单，密码没有加密
        String dbPass = user.getPassword();
        if (!password.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }

        //生成cookie
        String token = UUID.randomUUID().toString().replace("-", "");
        addCookie(response, token, user);
        return token;
    }

    @Override
    public User getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        User user = JSON.parseObject(redisUtil.get(COOKIE_NAME_TOKEN + "::" + token), User.class);
        //重置有效期
        if (user == null) {
            throw new GlobalException(CodeMsg.USER_NOT_LOGIN);
        }
        if (response != null) {
            addCookie(response, token, user);
        }
        return user;
    }

    @Override
    public User getByToken(String token) {
        return getByToken(null, token);
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        return getLoginUser(request, null);
    }

    @Override
    public User getLoginUser(HttpServletRequest request, HttpServletResponse response) {
        String paramToken = request.getParameter(UserServiceImpl.COOKIE_NAME_TOKEN);
        String cookieToken = getCookieValue(request, UserServiceImpl.COOKIE_NAME_TOKEN);
        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            // return null;
            throw new GlobalException(CodeMsg.USER_NOT_LOGIN);
        }
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        if (response == null) {
            return getByToken(token);
        }
        return getByToken(response, token);
    }

    private String getCookieValue(HttpServletRequest request, String cookiName) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length <= 0) {
            // return null;
            throw new GlobalException(CodeMsg.TOKEN_INVALID);
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookiName)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    private void addCookie(HttpServletResponse response, String token, User user) {
        //将token存入到redis
        redisUtil.set(COOKIE_NAME_TOKEN + "::" + token, JSON.toJSONString(user), TOKEN_EXPIRE);
        //将token写入cookie
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(TOKEN_EXPIRE);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchInsertOrUpdate(List<User> userList) {
        // 批量检查用户名是否存在
        List<String> usernameList = userList.stream().map(p -> p.getUsername()).collect(Collectors.toList());
        List<String> existUsernameList = userMapper.getExistByUsernameList(usernameList);

        List<User> needInsertList = new ArrayList<>();
        List<User> needUpdateList = new ArrayList<>();
        for (User user : userList) {
            if(existUsernameList.contains(user.getUsername())) {
                needUpdateList.add(user);
            } else {
                needInsertList.add(user);
            }
        }

        // 注意：mybatis批量操作入参最多1000条，如果超过1000条需要分组多次执行，这里先忽略
        // 批量插入新数据
        userMapper.batchInsert(needInsertList);

        // 批量更新旧数据
        userMapper.batchUpdate(needUpdateList);
    }
}
