package com.demo.ssm.po;

import java.util.List;

/**
 * Created by gmy on 15/7/20.
 */
public class UserQueryVo {
    //包装所需要的查询条件
    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    //用户查询条件
    private User user;

    //可以包装其他条件，比如订单、商品

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
