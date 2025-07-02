package com.example.club.mapper;

import com.example.club.entity.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper {

    List<User> selectAll();

    User selectById(int id);

    User findByStuIdAndPassword(String stuId, String password);

}
