package com.liuyanzhao.sens.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.liuyanzhao.sens.entity.User;
import com.liuyanzhao.sens.mapper.UserMapper;
import com.liuyanzhao.sens.repository.UserRepository;
import com.liuyanzhao.sens.service.UserService;
import com.liuyanzhao.sens.service.UserService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * 用户业务逻辑实现类
 * MyBatis Plus 版本
 */
@Service
public class UserServiceImpl2 implements UserService2 {


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
