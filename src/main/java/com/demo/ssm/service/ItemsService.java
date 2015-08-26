package com.demo.ssm.service;

import com.demo.ssm.po.ItemsCustom;
import com.demo.ssm.po.ItemsQueryVo;

import java.util.List;

/**
 * Created by gmy on 15/8/4.
 */
public interface ItemsService {
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;

    public ItemsCustom findById(Integer id) throws Exception;

    /**
     * @param id          查询商品的id
     * @param itemsCustom 修改商品的信息
     * @throws Exception
     */
    public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception;

    public void deleteById(Integer id) throws Exception;

}
