package com.example.club.controller;

import com.example.club.service.ActivityParticipantService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.club.entity.ClubActivity;
import com.example.club.entity.Result;

@RestController
@RequestMapping("/api/activity_participant")
public class ActivityParticipantController {
    @Autowired
    private ActivityParticipantService service;

    @PostMapping("/join")
    public String join(@RequestParam Integer activityId, @RequestParam Integer userId) {
        boolean success = service.joinActivity(activityId, userId);
        if (success) {
            return "{\"code\":\"success\",\"msg\":\"已提交参加申请\"}";
        } else {
            return "{\"code\":\"fail\",\"msg\":\"已申请或已参加该活动\"}";
        }
    }

    @GetMapping("/list")
    public Result getJoinedActivities(@RequestParam Integer userId) {
    // 使用Result包装响应数据，与其他接口格式保持一致
        return Result.success(service.getJoinedActivities(userId));
    }
}