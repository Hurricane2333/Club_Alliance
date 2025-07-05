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
        Map<String, Object> result = userService.login(stuId, password);
        if (result != null) {
            return Result.success(result);
        } else {
            return Result.error("学号或密码错误");
        }
    }
}
