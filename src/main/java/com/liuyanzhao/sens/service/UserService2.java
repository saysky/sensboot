package com.liuyanzhao.sens.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.liuyanzhao.sens.entity.User;

/**
 * 业务逻辑接口
 * MyBatis Plus 版本
 */
public interface UserService2 {


    /**
     * 新增
     *
     * @param user
     */
    void insert(User user);

    /**
     * 根据ID更新
     *
     * @param user
     */
    void update(User user);

    /**
     * 根据ID删除
     *
     * @param userId Id
     */
    void deleteById(Long userId);

    /**
     * 分页查询
     *
     * @param page 分页信息
     * @return 列表
     */
    Page<User> findAll(Page<User> page);

    /**
     * 根据Id查询
     *
     * @param userId
     * @return
     */
    User findById(Long userId);


}
