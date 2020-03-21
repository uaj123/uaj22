package cn.uaj.dao;

import cn.uaj.entity.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: wushaojie
 * @Date: 2020/2/9 17:54
 */
@Repository
public interface IAccountDao {
    /**
     * 查询所有账户
     * @return
     */
    @Select("select * from account")
    public List<Account> findAll();

    /**
     * 保存账户信息
     * @param account
     */
    @Insert("insert into account (username,password,money) values (#{username},#{password},#{money})")
    public void saveAccount(Account account);
}
