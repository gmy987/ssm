package com.demo.ssm.mapper;

import com.demo.ssm.po.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by gmy on 15/8/1.
 */
public class UserMapperTest {

    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");

    }

    @Test
    public void testFindUserById() throws Exception {
        UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
        User user = userMapper.findUserById(1);
        System.out.println(user);
    }

    @Test
    public void testInsertUser() throws Exception {

    }

    @Test
    public void testDeleteUser() throws Exception {

    }

    @Test
    public void testFindUserByName() throws Exception {

    }

    @Test
    public void testFindUserList() throws Exception {

    }

    @Test
    public void testFindUserCount() throws Exception {

    }

    @Test
    public void testFindUserByResultMap() throws Exception {

    }

    @Test
    public void testUpdateUser() throws Exception {

    }
}