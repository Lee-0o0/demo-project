package com.lee;

import com.lee.entity.UserPO;
import com.lee.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.*;

/**
 * @Description:
 * @Author: 水木子
 * @Datetime: 2023-2-2 10:39
 */

public class MyBatisParamTest {

    private String resource = "mybatis-config.xml"; // mybatis配置文件路径
    private InputStream inputStream = null; // 输入流
    private SqlSessionFactory sqlSessionFactory = null; // sqlSession工厂对象
    private SqlSession sqlSession = null; // sqlSession对象
    private UserMapper userMapper = null; // userMapper对象

    @Before
    public void setUp() throws Exception {
        // 初始化各属性，最终目的是获得userDao对象
        //注意是org.apache.ibatis.io中的Resources
        inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @After
    public void tearDown() throws Exception {
        // 关闭sqlSession对象
        sqlSession.close();
    }

    @Test
    public void test01(){
        UserPO user = userMapper.findUserById(1);
        System.out.println(user);
    }

    @Test
    public void test02(){
        List<UserPO> userList = userMapper.findUserByNameAndAge("张三", 10);
        userList.forEach(System.out::println);
    }

    @Test
    public void test03(){
        List<UserPO> userList = userMapper.findUserByIdsList(Arrays.asList(1,2,3));
        userList.forEach(System.out::println);
    }

    @Test
    public void test04(){
        List<UserPO> userList = userMapper.findUserByIdsArray(new int[]{1,2,3});
        userList.forEach(System.out::println);
    }

    @Test
    public void test05(){
        UserPO userPO = new UserPO();
        userPO.setId(1);
        userPO.setName("张三");
        UserPO user = userMapper.findUserByUserPO(userPO);
        System.out.println(user);
    }


    @Test
    public void test06(){
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("name","张三");

        List<UserPO> userList = userMapper.findUserByMap(map);
        userList.forEach(System.out::println);
    }


}
