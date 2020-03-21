package cn.uaj.entity;

import java.io.Serializable;

/**
 * @Author: wushaojie
 * @Date: 2020/2/9 17:47
 */
public class Account implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private Float money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", money=" + money +
                '}';
    }
}
