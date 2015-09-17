package com.demo.ssm.service.impl;

import com.demo.ssm.mapper.PointMapper;
import com.demo.ssm.po.Point;
import com.demo.ssm.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gmy on 15/9/17.
 */
@Service
public class PointServiceImpl implements PointService {
    @Autowired
    private PointMapper pointMapper;
    @Override
    public void insertPoint(Point point) throws Exception {
        pointMapper.insertPoint(point);
    }
}
