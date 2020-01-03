package com.liuyanzhao.sens.mapper.db1;

import com.liuyanzhao.sens.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author 言曌
 * @date 2020-01-03 17:48
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class Db1UserMapperTest {

    @Autowired
    private Db1UserMapper db1UserMapper;

    @Test
    public void findById() {

        User user = db1UserMapper.findById(1L);
        System.out.println(user);
    }
}
