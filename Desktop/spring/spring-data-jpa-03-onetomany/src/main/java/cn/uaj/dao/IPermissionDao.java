package cn.uaj.dao;

import cn.uaj.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: wushaojie
 * @Date: 2020/3/20 16:02
 */
public interface IPermissionDao extends JpaRepository<Permission,Long>, JpaSpecificationExecutor<Permission> {

}
