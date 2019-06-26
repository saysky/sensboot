package com.liuyanzhao.sens.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.liuyanzhao.sens.mapper.UserMapper;
import com.liuyanzhao.sens.entity.User;
import com.liuyanzhao.sens.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 用户业务逻辑实现类
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;


    @Override
    public void insert(User user) {
        user.setCreateTime(new Date());
        userMapper.insert(user);
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
    public User findById(Long userId) {
        return userMapper.selectById(userId);
    }


}
