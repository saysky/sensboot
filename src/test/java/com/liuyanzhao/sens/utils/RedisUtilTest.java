package com.liuyanzhao.sens.utils;


import com.alibaba.fastjson.JSON;
import com.liuyanzhao.sens.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 言曌
 * @date 2019-07-22 13:10
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisUtilTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testSet() {
        User user = new User();
        user.setId(1L);
        user.setUsername("admin");
        user.setPassword("123456");
        user.setNickname("管理员");
        redisUtil.set("user1", JSON.toJSONString(user));
    }

    @Test
    public void testGet() {
        User user1 = JSON.parseObject(redisUtil.get("user1"), User.class);
        System.out.println(user1);
    }
}