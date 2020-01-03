package com.liuyanzhao.sens.mapper.db2;

import com.liuyanzhao.sens.entity.User;

/**
 * Db1 数据源的 mapper 测试
 * @author 言曌
 * @date 2020-01-03 17:39
 */
public interface Db2UserMapper {

    /**
     * 根据ID获得用户
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     * 添加
     * @param user
     * @return
     */
    Integer insert(User user);
}
