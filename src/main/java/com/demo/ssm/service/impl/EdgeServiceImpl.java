package com.demo.ssm.service.impl;

import com.demo.ssm.mapper.EdgeMapper;
import com.demo.ssm.po.Edge;
import com.demo.ssm.service.EdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gmy on 15/9/17.
 */
@Service
public class EdgeServiceImpl implements EdgeService{
    @Autowired
    private EdgeMapper edgeMapper;
    @Override
    public void insertEdge(Edge edge) {
        edgeMapper.insertEdge(edge);
    }
}
