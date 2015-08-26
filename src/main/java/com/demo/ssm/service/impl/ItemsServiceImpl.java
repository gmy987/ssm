package com.demo.ssm.service.impl;

import com.demo.ssm.exception.CustomException;
import com.demo.ssm.mapper.ItemsMapper;
import com.demo.ssm.mapper.ItemsMapperCustom;
import com.demo.ssm.po.Items;
import com.demo.ssm.po.ItemsCustom;
import com.demo.ssm.po.ItemsQueryVo;
import com.demo.ssm.service.ItemsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gmy on 15/8/4.
 */
@Service
public class ItemsServiceImpl implements ItemsService {
    @Autowired
    private ItemsMapperCustom itemsMapperCustom;
    @Autowired
    private ItemsMapper itemsMapper;
    @Override
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
        //通过ItemsMapperCustom查询数据库
        return itemsMapperCustom.findItemsList(itemsQueryVo);
    }

    @Override
    public ItemsCustom findById(Integer id) throws Exception {
        Items items = itemsMapper.findById(id);
        //与业务功能相关的信息建议在service中抛出异常
        //与业务功能不相关的信息建议在controller中抛出
        if (items == null) {
            throw new CustomException("该商品不存在!");
        }
        // 中间对商品进行业务处理
        //....
        // 返回itemsCustom
        ItemsCustom itemsCustom = null;
        // 将items的内容拷贝到itemsCustom
        if (items != null) {
            itemsCustom = new ItemsCustom();
            BeanUtils.copyProperties(items, itemsCustom);
        }
        return itemsCustom;
    }

    @Override
    public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
        // 添加业务校验,通常在service接口进行关键参数的校验
        // 校验id是否为空,如果为空,抛出异常

        // 更新商品信息
        itemsCustom.setId(id);
        itemsMapper.updateItems(itemsCustom);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        itemsMapper.deleteById(id);
    }
}
