package cn.uaj.dao;

import cn.uaj.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: wushaojie
 * @Date: 2020/3/8 15:59
 */
public interface ICustomerDao extends JpaRepository<Customer,Long> , JpaSpecificationExecutor<Customer> {

}

