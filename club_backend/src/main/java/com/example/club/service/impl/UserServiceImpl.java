package com.example.club.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

import com.example.club.entity.User;
import com.example.club.mapper.UserMapper;
import com.example.club.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

        private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final long jwtExpirationMs = 86400000; // 24 hours

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public User selectById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public Map<String, Object> login(String stuId, String password) {
        User user = userMapper.findByStuIdAndPassword(stuId, password);
        if (user != null) {
            String token = Jwts.builder()
                    .setSubject(user.getStuId())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                    .signWith(key)
                    .compact();

            Map<String, Object> result = new HashMap<>();
            result.put("token", token);
            result.put("user", user);
            return result;
        }
        return null;
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }
}