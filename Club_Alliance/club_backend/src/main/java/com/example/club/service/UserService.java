package com.example.club.service;

import com.example.club.entity.Result;
import com.example.club.entity.User;
import com.example.club.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface UserService {



    List<User> selectAll();

    User selectById(int id);

    Result login(String stuId, String password);

    Result register(User user);

    int updateUser(User user);
}
