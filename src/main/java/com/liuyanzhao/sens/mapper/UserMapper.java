package com.liuyanzhao.sens.mapper;

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
     * @return 用户列表
     */
    List<User> findAll(Pagination page);


}

