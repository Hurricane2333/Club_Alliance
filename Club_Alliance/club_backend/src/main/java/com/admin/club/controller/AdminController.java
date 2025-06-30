package com.admin.club.controller;

import com.admin.club.entity.AdminClub;
import com.admin.club.entity.AdminUser;
import com.admin.club.mapper.AdminClubDao;
import com.admin.club.service.AdminClubService;
import com.admin.club.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private AdminClubService adminClubService;

    @CrossOrigin(origins="*")
    @GetMapping("/initial")
    public ArrayList<Long> initialize(){
        return adminUserService.infoCount();
    }

    @CrossOrigin(origins="*")
    @GetMapping("/recentClub")
    public List<AdminClub> recentClub(){
        return adminClubService.recentClub();
    }
}
