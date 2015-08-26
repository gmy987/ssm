package com.demo.ssm.mapper;


import com.demo.ssm.po.User;
import com.demo.ssm.po.UserQueryVo;

import java.util.List;

/**
 * Created by imac on 15/7/18.
 * Mapper接口 ，相当于用户管理的dao接口
 */
public interface UserMapper {

//    Mapper.java中的方法名必须和Mapper.xml文件中statement的id一致
//    Mapper.java方法中的输入参数必须和Mapper.xml中的parameterType一致
//    Mapper.java方法的返回参数类型和Mapper.xml中的resultType一致

    //根据id查询用户的信息
    public User findUserById(int id) throws Exception;

    //添加用户的信息
    public void insertUser(User user) throws Exception;

    //删除用户的信息
    public void deleteUser(int id) throws Exception;


    public List<User> findUserByName(String username);

    //用户信息的综合查询
    public List<User> findUserList(UserQueryVo userQueryVo) throws Exception;

    public int findUserCount(UserQueryVo userQueryVo) throws Exception;

    public User findUserByResultMap(int id) throws Exception;

    public void updateUser(User user) throws Exception;
}
