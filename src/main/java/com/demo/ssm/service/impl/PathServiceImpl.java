package com.demo.ssm.service.impl;

import com.demo.ssm.mapper.PathMapper;
import com.demo.ssm.po.Path;
import com.demo.ssm.service.PathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gmy on 15/9/17.
 */
@Service
public class PathServiceImpl implements PathService{
    @Autowired
    private PathMapper pathMapper;
    @Override
    public void insertPath(Path path) {
        pathMapper.insertPath(path);
    }
}
