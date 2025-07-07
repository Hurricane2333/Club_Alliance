package com.example.club.controller;

import com.example.club.entity.*;
import com.example.club.service.AdminClubActivityService;
import com.example.club.service.AdminClubMemberService;
import com.example.club.service.AdminClubService;
import com.example.club.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private AdminClubMemberService adminClubMemberService;
    @Autowired
    private AdminClubActivityService adminClubActivityService;

//    @CrossOrigin("*")
    @GetMapping("/initial")
    public ArrayList<Long> initialize(){
        return adminUserService.infoCount();
    }

//    @CrossOrigin("*")
    @GetMapping("/recentClub")
    public List<AdminClub> recentClub(){
        return adminClubService.recentClub();
    }

//    @CrossOrigin("*")
    @GetMapping("/allClub")
    public List<ClubResponse> allClub(){
        return adminClubService.allClub();
    }

//    @CrossOrigin("*")
    @DeleteMapping("/deleteClub/{id}")
    public boolean deleteSingleClub(@PathVariable int id){
        return adminClubService.deleteSingleClub(id);
    }

//    @CrossOrigin("*")
    @GetMapping("/searchLeader/{id}")
    public String searchLeader(@PathVariable int id) {
        AdminUser adminUser = adminUserService.findUserByID(id);
        if (adminUser != null)
            return adminUser.getStuName();
        else return null;
    }

//    @CrossOrigin("*")
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
            int clubId=adminClubService.findClubByName(adminClub.getClubName());
            createMember(clubId,adminClub.getPresidentId(),true);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

//    @CrossOrigin("*")
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

//    @CrossOrigin("*")
    @GetMapping("/searchClub/{name}")
    public List<ClubResponse> searchClub(@PathVariable String name){
        return adminClubService.searchClub(name);
    }

//    @CrossOrigin("*")
    @GetMapping("/allClubMember/{clubId}")
    public List<MemberResponse> allClubMember(@PathVariable int clubId){
        return adminClubMemberService.allClubMember(clubId);
    }

//    @CrossOrigin("*")
    @GetMapping("/searchMember/{clubId}/{name}")
    public List<MemberResponse> searchMember(@PathVariable int clubId, @PathVariable String name){
        return adminClubMemberService.searchMember(clubId, name);
    }

//    @CrossOrigin("*")
    @PostMapping("/createMember/{clubId}/{userId}/{isPresident}")
    public boolean createMember(@PathVariable int clubId,@PathVariable int userId,@PathVariable boolean isPresident){
        AdminClubMember adminCLubMember=new AdminClubMember();
        adminCLubMember.setClubId(clubId);
        adminCLubMember.setUserId(userId);
        adminCLubMember.setStatus("APPROVED");
        adminCLubMember.setApplyReason("管理员操作");
        Date date = new Date();
        date.setTime(date.getTime() + 28800000);
        adminCLubMember.setApplyTime(date);
        adminCLubMember.setReviewTime(date);
        if(isPresident)
            adminClubService.modifyPresident(clubId,userId);
        return adminClubMemberService.createMember(adminCLubMember);
    }

//    @CrossOrigin("*")
    @DeleteMapping("/removeMember/{clubId}/{userId}")
    public boolean removeMember(@PathVariable int clubId,@PathVariable int userId){
        return adminClubMemberService.removeMember(clubId,userId);
    }

//    @CrossOrigin("*")
    @PutMapping("/promoteMember/{clubId}/{userId}")
    public boolean promoteMember(@PathVariable int clubId,@PathVariable int userId){
        return adminClubService.modifyPresident(clubId,userId);
    }

//    @CrossOrigin("*")
    @GetMapping("/allClubToOperate")
    public List<ClubResponse> allClubToOperate(){
        return adminClubService.allClubToOperate();
    }

//    @CrossOrigin("*")
    @PutMapping("/passClub/{clubId}")
    public boolean passClub(@PathVariable int clubId){
        AdminClub adminClub=adminClubService.findClubById(clubId);
        createMember(clubId,adminClub.getPresidentId(),true);
        return adminClubService.passClub(clubId);
    }

//    @CrossOrigin("*")
    @PutMapping("/refuseClub/{clubId}")
    public boolean refuseClub(@PathVariable int clubId){
        return adminClubService.refuseClub(clubId);
    }

//    @CrossOrigin("*")
    @GetMapping("/searchClubToOperate/{name}")
    public List<ClubResponse> searchClubToOperate(@PathVariable String name){
        return adminClubService.searchClubToOperate(name);
    }

//    @CrossOrigin("*")
    @GetMapping("/allClubActivity")
    public List<ActivityResponse> allClubActivity(){
        return adminClubActivityService.allClubActivity();
    }

//    @CrossOrigin("*")
    @PutMapping("/passActivity/{activityId}")
    public boolean passActivity(@PathVariable int activityId){
        return adminClubActivityService.passActivity(activityId);
    }

//    @CrossOrigin("*")
    @PutMapping("/refuseActivity/{activityId}")
    public boolean refuseActivity(@PathVariable int activityId){
        return adminClubActivityService.refuseActivity(activityId);
    }

//    @CrossOrigin("*")
    @DeleteMapping("/deleteActivity/{activityId}")
    public boolean deleteActivity(@PathVariable int activityId){
        return adminClubActivityService.deleteActivity(activityId);
    }

//    @CrossOrigin("*")
    @GetMapping("/searchActivity/{name}")
    public List<ActivityResponse> searchActivity(@PathVariable String name){
        return adminClubActivityService.searchActivity(name);
    }

//    @CrossOrigin("*")
    @GetMapping("/allAdmin")
    public List<UserResponse> allAdmin(){
        return adminUserService.findAllAdmin();
    }

//    @CrossOrigin("*")
    @PostMapping(value="/createUser",consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean createUser(@RequestBody AdminUser adminUser){
        Date date = new Date();
        date.setTime(date.getTime() + 28800000);
        adminUser.setCreatedAt(date);
        try{
            adminUserService.createUser(adminUser);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

//    @CrossOrigin("*")
    @DeleteMapping("/deleteUser/{userId}")
    public boolean deleteSingleUser(@PathVariable int userId){
        return adminUserService.deleteSingleUser(userId);
    }

//    @CrossOrigin("*")
    @GetMapping("/searchAdmin/{stuName}")
    public List<UserResponse> searchAdmin(@PathVariable String stuName){
        return adminUserService.searchAdmin(stuName);
    }

//    @CrossOrigin("*")
    @PutMapping(value="/editAdmin",consumes =MediaType.APPLICATION_JSON_VALUE)
    public boolean editAdmin(@RequestBody AdminUser adminUser){
        try{
            adminUserService.editAdmin(adminUser);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

//    @CrossOrigin("*")
    @GetMapping("/allStudent")
    public List<UserResponse> allStudent(){
        return adminUserService.findAllStudent();
    }

//    @CrossOrigin("*")
    @GetMapping("/searchStudent/{stuName}")
    public List<UserResponse> searchStudent(@PathVariable String stuName){
        return adminUserService.searchStudent(stuName);
    }

//    @CrossOrigin("*")
    @PutMapping(value="/editStudent",consumes =MediaType.APPLICATION_JSON_VALUE)
    public boolean editStudent(@RequestBody AdminUser adminUser){
        try{
            adminUserService.editStudent(adminUser);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
}
