package com.liuyanzhao.sens.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.liuyanzhao.sens.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 业务逻辑接口
 *
 * MyBatis Plus 版本
 * @author liuyanzhao
 */
public interface UserService {


    /**
     * 新增
     *
     * @param user
     */
    void insert(User user);

    /**
     * 新增，测试事务
     *
     * @param user
     */
    void insertTestTransactional(User user);

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
     * 不分页查询
     *
     * @return 列表
     */
    List<User> findAll();

    /**
     * 根据Id查询
     *
     * @param userId
     * @return
     */
    User findById(Long userId);

    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 登录逻辑
     *
     * @param response
     * @param username
     * @param password
     * @return
     */
    String login(HttpServletResponse response, String username, String password);

    /**
     * 根据token获得用户信息
     *
     * @param response
     * @param token
     * @return
     */
    User getByToken(HttpServletResponse response, String token);

    /**
     * 根据token获得用户信息
     * @param token
     * @return
     */
    User getByToken(String token);


    /**
     * 获得当前登录用户
     * @param request
     * @return
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 获得当前登录用户
     * @param request
     * @param response
     * @return
     */
    User getLoginUser(HttpServletRequest request, HttpServletResponse response);

    /**
     * 批量添加
     * @param userList
     */
    void batchInsertOrUpdate(List<User> userList);

}
