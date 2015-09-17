package com.demo.ssm.service.impl;

import com.demo.ssm.mapper.AddressMapper;
import com.demo.ssm.po.Address;
import com.demo.ssm.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gmy on 15/9/17.
 */
@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    AddressMapper addressMapper;
    @Override
    public void insertAddress(Address address) {
        addressMapper.insertAddress(address);
    }
}
