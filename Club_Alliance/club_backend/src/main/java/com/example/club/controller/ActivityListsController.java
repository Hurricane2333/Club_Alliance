package com.example.club.controller;

import com.example.club.entity.ClubActivity;
import com.example.club.entity.Result;
import com.example.club.mapper.ClubActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activitylists")
public class ActivityListsController {

    @Autowired
    private ClubActivityMapper clubActivityMapper;

    @GetMapping
    public Result getAllActivities() {
        try {
            List<ClubActivity> Allactivities = clubActivityMapper.selectAllActivities();
            return Result.success(Allactivities);
        } catch (Exception e) {
            return Result.error("获取活动列表失败: " + e.getMessage());
        }
    }
}