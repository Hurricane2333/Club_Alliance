package com.example.club.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

import com.example.club.entity.Club;
import com.example.club.entity.User;
import com.example.club.mapper.ClubMapper;
import com.example.club.mapper.UserMapper;
import com.example.club.entity.Result;
import com.example.club.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final ClubMapper clubMapper;

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final long jwtExpirationMs = 86400000; // 24 hours

    public UserServiceImpl(UserMapper userMapper, ClubMapper clubMapper) {
        this.userMapper = userMapper;
        this.clubMapper = clubMapper;
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
    public Result login(String stuId, String password) {
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

            Club club = clubMapper.findByPresidentId(user.getUserId());
            if (club != null) {
                result.put("isPresident", true);
                result.put("clubName", club.getClubName());
                result.put("clubId", club.getClubId());
            } else {
                result.put("isPresident", false);
            }

            return Result.success(result);
        }
        return Result.error("学号或密码错误");
    }

    @Override
    @Transactional
    public Result register(User user) {
        User existingUser = userMapper.findByStuId(user.getStuId());
        if (existingUser != null) {
            return Result.error("学号已存在");
        }


        user.setAvatar("default_avatar.png");
        user.setIsAdmin(0);
        user.setCreatedAt(new Date());

        int result = userMapper.insertUser(user);
        if (result > 0) {
            return Result.success();
        } else {
            return Result.error("注册失败");
        }
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }
}