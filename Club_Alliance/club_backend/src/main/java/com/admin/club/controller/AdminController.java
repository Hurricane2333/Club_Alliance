package com.admin.club.controller;

import com.admin.club.entity.AdminClub;
import com.admin.club.entity.AdminUser;
import com.admin.club.entity.ClubResponse;
import com.admin.club.mapper.AdminClubDao;
import com.admin.club.service.AdminClubService;
import com.admin.club.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Date;
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

    @CrossOrigin(origins="*")
    @GetMapping("/allClub")
    public List<ClubResponse> allClub(){
        return adminClubService.allClub();
    }

    @CrossOrigin(origins="*")
    @DeleteMapping("/deleteClub/{id}")
    public boolean deleteSingleClub(@PathVariable Integer id){
        return adminClubService.deleteSingleClub(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/searchLeader/{id}")
    public String searchLeader(@PathVariable Integer id) {
        AdminUser adminUser = adminUserService.findUserByID(id);
        if (adminUser != null)
            return adminUser.getStuName();
        else return null;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value="/createClub",consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean createClub(@RequestBody AdminClub adminClub){
        adminClub.setFavoriteCount(0);
        adminClub.setCurrentMembers(0);
        adminClub.setStatus("APPROVED");
        Date date = new Date();
        date.setTime(date.getTime() + 28800000);
        adminClub.setCreatedAt(date);
        try{
            adminClubService.createClub(adminClub);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    @CrossOrigin(origins = "*")
    @PutMapping(value="/editClub",consumes =MediaType.APPLICATION_JSON_VALUE)
    public boolean editClub(@RequestBody AdminClub adminClub){
        try{
            adminClubService.editClub(adminClub);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    @CrossOrigin(origins="*")
    @GetMapping("/searchClub/{name}")
    public List<ClubResponse> searchClub(@PathVariable String name){
        return adminClubService.searchClub("%"+name+"%");
    }
}
