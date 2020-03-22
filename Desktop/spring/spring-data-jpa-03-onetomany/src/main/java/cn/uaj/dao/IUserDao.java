package cn.uaj.dao;

import cn.uaj.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: wushaojie
 * @Date: 2020/3/20 16:01
 */
public interface IUserDao extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

}
