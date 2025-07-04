package com.example.club.controller;

import com.example.club.service.ActivityParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}