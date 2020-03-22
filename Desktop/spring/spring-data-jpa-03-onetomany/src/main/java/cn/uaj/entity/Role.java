package cn.uaj.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: wushaojie
 * @Date: 2020/3/20 15:53
 * 角色
 */
@Entity
@Table(name = "sys_role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "role_name")
    private String roleName;

    /*配置角色到权限的多对多关系*/
    @ManyToMany(targetEntity = Permission.class,cascade = CascadeType.ALL)
    @JoinTable(
            name = "sys_role_permission",
            joinColumns = {@JoinColumn(name = "s_role_id",referencedColumnName = "role_id")}
            ,inverseJoinColumns = {@JoinColumn(name = "s_permission_id",referencedColumnName = "permission_id")}
    )
    private Set<Permission> permissions = new HashSet<>();
    /*配置角色到用户的多对多关系*/
    @ManyToMany(mappedBy = "roles") // 放弃维护权
    private Set<User> users = new HashSet<>();

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
