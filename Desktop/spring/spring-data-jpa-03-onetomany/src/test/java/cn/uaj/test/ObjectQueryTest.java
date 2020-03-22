package cn.uaj.test;

import cn.uaj.dao.ICustomerDao;
import cn.uaj.dao.ILinkManDao;
import cn.uaj.entity.Customer;
import cn.uaj.entity.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

/**
 * 对象导航查询，通过查询一个对象，并通过这个对象查询所有关联对象
 * @Author: wushaojie
 * @Date: 2020/3/20 21:13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ObjectQueryTest {

    @Autowired
    private ICustomerDao customerDao;

    @Autowired
    private ILinkManDao linkManDao;

    @Test
    @Transactional // 解决 no session问题
    public void query(){
        Optional<Customer> optionalCustomer = customerDao.findById(2L);
        Customer customer = optionalCustomer.orElse(new Customer());
        System.out.println(customer);
        /*对象导航查询该客户下的所有联系人*/
        Set<LinkMan> linkMans = customer.getLinkMans();
        for (LinkMan linkMan :linkMans) {
            System.out.println(linkMan.getLkmName());
        }


    }
    /*
     * 对象导航查询从一的一方查多的一方默认使用延迟加载的形式查询的，
     * 调用get方法时不会立即发送查询，只在使用关联对象时才会发送查询
     * 要使用立即加载，需要在一的一方修改配置：fetch : 需要配置在多表映射关系的注解上
     * */
    @Test
    @Transactional // 解决 no session问题
    public void query1(){
        /*从客户对象导航查询他的所有关联对象*/
        Optional<Customer> optionalCustomer = customerDao.findById(2L);
        Customer customer = optionalCustomer.orElse(new Customer());
        System.out.println(customer);
        /*对象导航查询该客户下的所有联系人*/
        Set<LinkMan> linkMans = customer.getLinkMans();
        for (LinkMan linkMan :linkMans) {
            System.out.println(linkMan.getLkmName());
        }


    }/*
     * 对象导航查询从多的一方查一的一方默认使用立即加载的形式查询的，
     * 调用get方法时立即发送查询
     * 要使用延迟加载，需要在多的一方修改配置：fetch : 需要配置在多表映射关系的注解上
     * */
    @Test
    @Transactional // 解决 no session问题
    public void query2(){
        /*从客户对象导航查询他的所有关联对象*/
        Optional<LinkMan> optionalLinkMan = linkManDao.findById(3L);
        LinkMan linkMan = optionalLinkMan.orElse(new LinkMan());
        System.out.println(linkMan);
        /*对象导航查询该客户下的所有联系人*/
        Customer customer = linkMan.getCustomer();
        System.out.println(customer);

    }
}
