package cn.uaj.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 客户实体类
 * @Author: wushaojie
 * @Date: 2020/3/6 11:43
 * 1,实体类和表的映射关系
 * @Entity :声明实体类
 * @Table ：配置实体类和表的映射关系，
 *      name: 配置数据库表的名称
 * 2,类中属性和表中字段的映射关系
 * @Id ：声明主键的配置
 * @GeneratedValue ： 配置主键的生成策略，一般使用strategy属性中的前两个
 *      strategy :
 *          GenerationType.IDENTITY -- 自增，mysql
 *              * 底层数据库必须支持自动增长
 *          GenerationType.SEQUENCE : 序列，oracle
 *              * 底层数据库必须支持序列
 *          GenerationType.TABLE : jpa提供的一种机制，通过一张数据库表的形式帮助我们完成主键自增
 *          GenerationType.AUTO : 由程序自动的帮助我们选择主键生成策略
 *
 * @Column ：配置属性和字段的映射关系
 *      name : 数据库表中字段的名称
 */
@Entity
@Table(name = "cst_customer")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long custId; // 客户主键
    @Column(name = "cust_address")
    private String custAddress; // 客户联系地址
    @Column(name = "cust_industry")
    private String custIndustry; // 客户所属行业
    @Column(name = "cust_level")
    private String custLevel; // 客户级别
    @Column(name = "cust_name")
    private String custName; // 客户名称
    @Column(name = "cust_phone")
    private String custPhone; // 客户联系电话
    @Column(name = "cust_source")
    private String custSource; // 客户信息来源

    // 配置客户和联系人之间的关系（一对多）
    /**
     * 使用注解的形式配置多表关系
     * 1，声明关系
     *      @OneToMany :配置一对多关系
     *          targetEntity:对方对象的字节码
     *  2，配置外键（中间表）：
     *          @JoinColumn:配置外键
     *              name:外键字段名称
     *              referencedColumnName:参照的主表的主键字段名称
     *
     *  3，在客户实体类上添加了外键配置，所以客户就具备了维护外键的作用
     */
    /*@OneToMany(targetEntity = LinkMan.class)
    @JoinColumn(name = "lkm_cust_id",referencedColumnName = "cust_id")*/
    // 放弃一的一方对外键的维护权，mapperBy="多的一方实体类中管理一的一方的字段名称"
    /*
    *   cascade : 配置级联
    *          CascadeType.ALL ： 所有操作
    *           CascadeType.MERGE : 跟新
    *           CascadeType.REMOVE : 删除
    *           CascadeType.PERSIST : 保存
    *   fetch : 配置加载方式
    *          = FetchType.LAZY ：延迟加载
    *          = FetchType.EAGER : 立即加载，一般不推荐改为立即加载
    * */
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private Set<LinkMan> linkMans = new HashSet<LinkMan>();


    public Set<LinkMan> getLinkMans() {
        return linkMans;
    }

    public void setLinkMans(Set<LinkMan> linkMans) {
        this.linkMans = linkMans;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custAddress='" + custAddress + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custName='" + custName + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", custSource='" + custSource + '\'' +
                ", linkMans=" + linkMans +
                '}';
    }
}
