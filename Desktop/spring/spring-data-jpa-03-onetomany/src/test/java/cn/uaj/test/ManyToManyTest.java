package cn.uaj.test;

import cn.uaj.dao.IPermissionDao;
import cn.uaj.dao.IRoleDao;
import cn.uaj.dao.IUserDao;
import cn.uaj.entity.Permission;
import cn.uaj.entity.Role;
import cn.uaj.entity.User;
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
 * @Date: 2020/3/20 16:44
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ManyToManyTest {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private IPermissionDao permissionDao;
    @Test
    @Transactional
    @Rollback(false)
    public void testAdd(){
        User user = new User();
        user.setUserAge(12);
        user.setUserName("小米");

        Role role = new Role();
        role.setRoleName("管理员");

        Permission permission = new Permission();
        permission.setPermissionName("查询");

        /*配置角色和用户关系,如果多对多双方都有维护权会报异常，解决：被动一方放弃维护权，角色放弃*/
        /*配置用户到角色关系，可以对中间表中数据进行维护*/
        user.getRoles().add(role);
        /*配置角色到用户关系，可以对中间表中数据进行维护*/
        role.getUsers().add(user);
        /*配置角色到权限关系，可以对中间表中数据进行维护*/
        role.getPermissions().add(permission);
        /*配置权限到角色关系，可以对中间表中数据进行维护*/
        permission.getRoles().add(role);

        userDao.save(user);
        roleDao.save(role);
        permissionDao.save(permission);

    }

    /**
     * 级联添加用户，角色和权限也一起被添加
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCascadeAdd(){
        User user = new User();
        user.setUserAge(23);
        user.setUserName("小米1");

        Role role = new Role();
        role.setRoleName("管理员1");

        Permission permission = new Permission();
        permission.setPermissionName("查询1");

        /*配置角色和用户关系,如果多对多双方都有维护权会报异常，解决：被动一方放弃维护权，角色放弃*/
        /*配置用户到角色关系，可以对中间表中数据进行维护*/
        user.getRoles().add(role);
        /*配置角色到用户关系，可以对中间表中数据进行维护*/
        role.getUsers().add(user);
        /*配置角色到权限关系，可以对中间表中数据进行维护*/
        role.getPermissions().add(permission);
        /*配置权限到角色关系，可以对中间表中数据进行维护*/
        permission.getRoles().add(role);

        userDao.save(user);

    }

    /**
     * 级联删除用户，包括该用户下的所有角色和角色下的所有权限，实际开发中慎用
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCascadeRemove(){
        Optional<User> optionalUser = userDao.findById(1L);
        /*orElse 当optionalUser不为null时，返回user,为null时返回默认值new User(),避免空指针异常*/
        User user = optionalUser.orElse(new User());
        System.out.println(user.toString());
        userDao.delete(user);
    }
}
