package com.liuyanzhao.sens.mapper;

import com.liuyanzhao.sens.entity.User;
import com.liuyanzhao.sens.mapper.db1.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author 言曌
 * @date 2020-01-01 16:10
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 测试批量插入返回自动ID列表
     */
    @Test
    public void batchInsert() {
        Date now = new Date();
        List<User> userList = new ArrayList<>();
        User user = new User("zhangsan", "张三", "111111", "zhangsan@123.com", "", 1, now, "system", now, "system");
        User user2 = new User("lisi", "李四", "111111", "lisi@123.com", "", 1, now, "system", now, "system");
        User user3 = new User("wangwu", "王五", "111111", "wangwu@123.com", "", 1, now, "system", now, "system");
        User user4 = new User("liliu", "李六", "111111", "liliu@123.com", "", 1, now, "system", now, "system");
        User user5 = new User("chenqi", "陈七", "111111", "chenqi@123.com", "", 1, now, "system", now, "system");
        userList.add(user);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        userMapper.batchInsert(userList);

        // 获得ID列表
        List<Long> userIdList = userList.stream().map(p -> p.getId()).collect(Collectors.toList());
        System.out.println(userIdList);
    }
}
