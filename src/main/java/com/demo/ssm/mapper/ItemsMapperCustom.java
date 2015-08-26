package com.demo.ssm.mapper;


import com.demo.ssm.po.ItemsCustom;
import com.demo.ssm.po.ItemsQueryVo;

import java.util.List;

/**
 * Created by gmy on 15/8/4.
 */
public interface ItemsMapperCustom {
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
}
