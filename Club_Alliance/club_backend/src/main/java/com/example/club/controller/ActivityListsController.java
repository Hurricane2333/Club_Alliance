package com.example.club.controller;

import com.example.club.entity.ClubActivity;
import com.example.club.entity.Result;
import com.example.club.mapper.ClubActivityMapper;
import com.example.club.service.ClubActivityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activitylists")
public class ActivityListsController {

    @Autowired
    private ClubActivityMapper clubActivityMapper;

    @Autowired
    private ClubActivityService clubActivityService;

    @GetMapping
    public Result getAllActivities() {
        try {
            List<ClubActivity> Allactivities = clubActivityMapper.selectAllActivities();
            return Result.success(Allactivities);
        } catch (Exception e) {
            return Result.error("获取活动列表失败: " + e.getMessage());
        }
    }

    @GetMapping("/detail/{id}")
    public Result getActivityDetail(@PathVariable Integer id) {
        try {
            ClubActivity activity = clubActivityService.getActivityDetailById(id);
            if (activity != null) {
                return Result.success(activity);
            } else {
                return Result.error("未找到该活动");
            }
        } catch (Exception e) {
            return Result.error("获取活动详情失败: " + e.getMessage());
        }
    }

}