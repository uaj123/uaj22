package cn.uaj.dao;

import cn.uaj.entity.LinkMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 联系人接口
 * @Author: wushaojie
 * @Date: 2020/3/8 16:18
 */
public interface ILinkManDao extends JpaRepository<LinkMan,Long>, JpaSpecificationExecutor<LinkMan> {

}
