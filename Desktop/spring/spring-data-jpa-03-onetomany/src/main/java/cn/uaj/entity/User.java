package cn.uaj.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: wushaojie
 * @Date: 2020/3/20 15:52
 * 用户
 */
@Entity
@Table(name = "sys_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_age")
    private Integer userAge;

    /*配置用户到角色的多对多关系
    *   配置多对多的映射关系
    *       1，声明表关系的配置
    *           @ManyToMany(targetEntity = 对方实体类的字节码)
    *       2，配置中间表（包含两个外键）
    *           @JoinTable(name = "中间表名称",
    *           joinColumns = {@JoinColumn(name = "中间表当前对象的外键",referencedColumnName = "当前实体类在数据库表中的主键")}，
    *            inverseJoinColumns = {@JoinColumn(name = "中间表对方对象的外键",referencedColumnName = "对方实体类在数据库表中的主键")}
     *           )
    * */
    @ManyToMany(targetEntity = Role.class,cascade = CascadeType.ALL) // 多对多
    @JoinTable(name = "sys_user_role",
        joinColumns = {@JoinColumn(name = "sys_user_id",referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "sys_role_id",referencedColumnName = "role_id")}
    )
    private Set<Role> roles = new HashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

}
