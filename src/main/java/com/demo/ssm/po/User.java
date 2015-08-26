package com.demo.ssm.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by imac on 15/7/16.
 * 要实现UserMapper的二级缓存，需要User实现序列化接口，
 * 目的是为了将缓存取出执行反序列化操作，因为二级缓存数据存储介质多种多样
 */
public class User implements Serializable{
    //属性名和数据库表的字段对应
    private int id;
    private String username;
    private String sex;
    private Date birthday;
    private String address;
    private List<Orders> ordersList;

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", ordersList=" + ordersList +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
