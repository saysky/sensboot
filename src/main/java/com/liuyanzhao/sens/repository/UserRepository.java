package com.liuyanzhao.sens.repository;

import com.liuyanzhao.sens.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author 言曌
 * @date 2019-06-26 17:44
 */
public interface UserRepository  extends JpaRepository<User,Long> {

}
