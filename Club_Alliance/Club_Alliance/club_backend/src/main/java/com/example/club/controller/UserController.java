package com.example.club.controller;

import com.example.club.entity.Result;
import com.example.club.entity.User;
import com.example.club.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    //查询数据
    @GetMapping("/selectAll")
    public Result selectAll(){
        List<User> list = userService.selectAll();
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        User user = userService.selectById(id);
        if (user != null) {
            return Result.success(user);
        } else {
            return Result.error("404", "User not found");
        }
    }

    @PutMapping("/{id}")
    public Result updateUser(@PathVariable Integer id, @RequestBody User user) {
        user.setUserId(id);
        int res = userService.updateUser(user);
        if (res > 0) {
            return Result.success();
        } else {
            return Result.error("Failed to update user");
        }
    }

    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> credentials) {
        String stuId = credentials.get("stuId");
        String password = credentials.get("password");
        return userService.login(stuId, password);
    }

    @PostMapping("/register")
    public Result register(@RequestBody Map<String, String> credentials) {
        User user = new User();
        user.setStuId(credentials.get("stuId"));
        user.setStuName(credentials.get("stuName"));
        user.setEmail(credentials.get("email"));
        user.setPassword(credentials.get("password"));
        return userService.register(user);
    }
}
