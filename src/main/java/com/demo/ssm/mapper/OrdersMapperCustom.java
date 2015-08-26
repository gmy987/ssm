package com.demo.ssm.mapper;


import com.demo.ssm.po.Orders;
import com.demo.ssm.po.OrdersCustom;
import com.demo.ssm.po.User;

import java.util.List;

/**
 * Created by gmy on 15/7/25.
 */
public interface OrdersMapperCustom {
    //查询订单关联查询用户信息
    public List<OrdersCustom> findOrdersUser() throws Exception;

    public List<Orders> findOrdersUserResultMap() throws Exception;

    public List<Orders> findOrderDetail() throws Exception;

    public List<User> findUserAndOrders() throws Exception;

    public List<Orders> findOrdersUserLazyLoading() throws Exception;
}
