package com.demo.ssm.po;

/**
 * Created by gmy on 15/9/17.
 */
public class Address {
    int address_id;
    int node_id;
    String name;

    public Address(int address_id,int node_id, String name) {
        this.address_id = address_id;
        this.node_id = node_id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Address{" +
                "address_id=" + address_id +
                ", node_id=" + node_id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public int getNode_id() {
        return node_id;
    }

    public void setNode_id(int node_id) {
        this.node_id = node_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
