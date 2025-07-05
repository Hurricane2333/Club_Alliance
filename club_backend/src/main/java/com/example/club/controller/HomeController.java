package com.example.club.controller;

import com.example.club.entity.Club;
import com.example.club.service.ClubService;

import com.example.club.entity.ClubActivity;
import com.example.club.service.ClubActivityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.club.entity.Result;
import com.example.club.mapper.ClubActivityMapper;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    private  ClubService clubService;
    @Autowired
    private ClubActivityService clubActivityService;

    /*public HomeController(ClubService clubService) {
        this.clubService = clubService;
    }*/

    @GetMapping("/clubs")
    public Result getHomeClubs() {
        try {
            List<Club> clubs = clubService.getHomeRecommendedClubs();
            return Result.success(clubs != null ? clubs : "NULL");
        } catch (Exception e) {
            return Result.error("获取首页推荐社团失败"+ e.getMessage());
        }
    }
   @GetMapping("/activities")
    public Result getRecentActivities() {
        try {
            List<ClubActivity> activities = clubActivityService.getRecentActivities();
            return Result.success(activities != null ? activities : "NULL");
        } catch (Exception e) {
            return Result.error("获取最近活动失败" + e.getMessage());
        }
    }
    /*@GetMapping("/clubs/{clubId}")
    public Result getClubDetail(@PathVariable Integer clubId) {
        try {
            Club club = clubService.getClubById(clubId);
            if (club == null) {
                return Result.error("社团不存在");
            }
            return Result.success(club);
        } catch (Exception e) {
            return Result.error("获取社团详情失败");
        }
    }*/
}

