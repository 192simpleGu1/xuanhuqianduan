package com.example.infusion.mapper;

import com.example.infusion.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
* @author admin
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-03-14 23:26:07
* @Entity generator.domain.User
*/
public interface UserMapper extends BaseMapper<User> {

    @Update("update user set password=#{password} where phone=#{phone}")
    public int updateByPhone(String password,String phone);
}




