package com.demo.ssm.mapper;

import com.demo.ssm.po.Items;

/**
 * Created by gmy on 15/8/5.
 */
public interface ItemsMapper {
    public Items findById(int id) throws Exception;

    public void updateItems(Items items) throws Exception;

    public void deleteById(int id) throws Exception;
}
