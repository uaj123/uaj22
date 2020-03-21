package cn.uaj.service;

import cn.uaj.entity.Account;

import java.util.List;

/**
 * @Author: wushaojie
 * @Date: 2020/2/9 17:55
 */

public interface IAccountService {
    /**
     * 查询所有账户
     * @return
     */
    public List<Account> findAll();

    /**
     * 保存账户信息
     * @param account
     */
    public void saveAccount(Account account);
}
