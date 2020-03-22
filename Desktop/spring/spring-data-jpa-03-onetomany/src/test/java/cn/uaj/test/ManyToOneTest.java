package cn.uaj.test;

import cn.uaj.dao.ILinkManDao;
import cn.uaj.dao.ICustomerDao;
import cn.uaj.entity.LinkMan;
import cn.uaj.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @Author: wushaojie
 * @Date: 2020/3/8 16:55
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ManyToOneTest {

    @Autowired
    private ICustomerDao customerDao;

    @Autowired
    private ILinkManDao linkManDao;

    /**
     * 保存一个客户，保存一个联系人
     *  效果：客户和联系人作为独立的数据保存到数据库中
     *      联系人的外键为空
     *  原因？
     *      实体类中没有配置关系
     */
    @Test
    @Transactional //配置事务
    @Rollback(false) //不自动回滚
    public void testAdd() {
        //创建一个客户，创建一个联系人
        Customer customer = new Customer();
        customer.setCustName("百度");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李");

        /**
         * 配置了客户到联系人的关系
         *      从客户的角度上：发送两条insert语句，发送一条更新语句更新数据库（更新外键）
         * 由于我们配置了客户到联系人的关系：客户可以对外键进行维护
         */
        customer.getLinkMans().add(linkMan);


        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    @Test
    @Transactional //配置事务
    @Rollback(false) //不自动回滚
    public void testAdd1() {
        //创建一个客户，创建一个联系人
        Customer customer = new Customer();
        customer.setCustName("百度1");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李2");

        /**
         * 配置了联系人到客户的关系
         *      从联系人的角度上：发送两条insert语句，
         * 由于我们配置了联系人到客户的关系：联系人可以对外键进行维护
         */
        linkMan.setCustomer(customer);


        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    @Test
    @Transactional //配置事务
    @Rollback(false) //不自动回滚
    public void testAdd2() {
        //创建一个客户，创建一个联系人
        Customer customer = new Customer();
        customer.setCustName("sss");


        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李8");

        /**
         * 配置了联系人到客户的关系
         *      从联系人的角度上：发送两条insert语句，
         * 由于我们配置了联系人到客户的关系：联系人可以对外键进行维护
         * 多的一方对一的一方的外键维护权
         *
         * 配置了客户到联系人的关系
         *      从客户的角度上：发送两条insert语句，发送一条更新语句更新数据库（更新外键）
         * 由于我们配置了客户到联系人的关系：客户可以对外键进行维护
         * 一的一方对多的一方的外键维护权
         *
         * 此时两个都配置了，就会多执行一条update语句，可以通过放弃客户对外键的维护权
         * ，就是通过放弃一的一方对外键的维护权，即可解决
         */
        linkMan.setCustomer(customer);
        customer.getLinkMans().add(linkMan);

        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    @Test
    @Transactional //配置事务
    @Rollback(false) //不自动回滚
    public void testAdd3() {
        //根据id查询一个客户，
        Optional<Customer> optional = customerDao.findById(2L);
        // 判断查询的类对象是否存在
        boolean flag = optional.isPresent();
        Customer customer = null;
        if (flag) {
            // 存在就获取
            customer = optional.get();
        }else {
            customer = new Customer();
            customer.setCustName("sss");
        }
        // 创建一个联系人
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小55李65");

        /**
         * 配置了联系人到客户的关系
         *      从联系人的角度上：发送两条insert语句，
         * 由于我们配置了联系人到客户的关系：联系人可以对外键进行维护
         * 多的一方对一的一方的外键维护权
         *
         *
         **/
        linkMan.setCustomer(customer);
        customer.getLinkMans().add(linkMan);

        customerDao.save(customer);

    }

    @Test
    @Transactional
    @Rollback(false)
    // 级联添加
    public void testCascadeAdd(){
        Customer customer = new Customer();
        customer.setCustName("掌声");
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李99");

        linkMan.setCustomer(customer);
        customer.getLinkMans().add(linkMan);

        customerDao.save(customer);
    }
    @Test
    @Transactional
    @Rollback(false)
    // 级联删除
    public void testCascadeRemove(){
        Optional<Customer> optionalCustomer = customerDao.findById(3L);
        if (optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            customerDao.delete(customer);
        }
    }
}
