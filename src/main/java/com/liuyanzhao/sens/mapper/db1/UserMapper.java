package com.liuyanzhao.sens.mapper.db1;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.liuyanzhao.sens.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author liuyanzhao
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询所有
     *
     * @param page
     * @return 用户列表
     */
    List<User> findAll(Pagination page);

    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 批量插入
     * @param users
     */
    void batchInsert(List<User> users);


    /**
     * 批量插入
     * @param users
     */
    void batchUpdate(List<User> users);

    /**
     * 批量查询哪些用户名已存在，返回列表的index
     * @param usernameList
     * @return
     */
    List<String> getExistByUsernameList(List<String> usernameList);
}

