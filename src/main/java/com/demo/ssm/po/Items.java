package com.demo.ssm.po;

import com.demo.ssm.validation.ValidGroup1;
import com.demo.ssm.validation.ValidGroup2;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by gmy on 15/7/27.
 */
public class Items {
    private int id;
    //校验名称在1-30个字符之间
    //message是校验出错显示的信息
    //groups指定属于哪个分组,可以指定多个分组
    @Size(min = 1,max = 30,message = "{items.name.length.error}",groups = {ValidGroup1.class})
    private String name;
    private double price;
    private String detail;
    //非空校验
    @NotNull(message = "{items.createtime.isNull}",groups = {ValidGroup2.class})
    private Date createtime;
    private String pic;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Items{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", detail='" + detail + '\'' +
                '}';
    }
}
