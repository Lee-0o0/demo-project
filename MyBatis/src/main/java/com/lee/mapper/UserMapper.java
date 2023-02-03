package com.lee.mapper;

import com.lee.entity.UserPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: 水木子
 * @Datetime: 2023-2-2 10:39
 */
public interface UserMapper {


    UserPO findUserById(int id);

    List<UserPO> findUserByNameAndAge(@Param("name") String name, int age);

    List<UserPO> findUserByIdsList(List<Integer> ids);

    List<UserPO> findUserByIdsArray(int[] ids);

    UserPO findUserByUserPO(@Param("user") UserPO user);

    List<UserPO> findUserByMap(Map<String, Object> map);
}
