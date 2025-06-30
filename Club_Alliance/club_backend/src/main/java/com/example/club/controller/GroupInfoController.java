package com.example.club.controller;

import com.example.club.entity.GroupInfo;
import com.example.club.enums.ClubCategory;
import com.example.club.service.GroupInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("group")
public class GroupInfoController {

    @Autowired
    private GroupInfoService groupInfoService;

    @GetMapping("selectAll")
    public List<GroupInfo> queryAllGroupInfo(){
        return groupInfoService.queryAllGroupInfo();
    }

    @GetMapping("selectName/{clubName}")
    public List<GroupInfo> queryGroupInfoByName(@PathVariable("clubName") String clubName){
        return groupInfoService.queryGroupInfoByName(clubName);
    }

    @GetMapping("selectCategory/{category}")
    public List<GroupInfo> queryGroupInfoByCategory(@PathVariable("category") ClubCategory category){
        return groupInfoService.queryGroupInfoByCategory(category);
    }

    @GetMapping("selectId/{clubId}")
    public GroupInfo queryGroupInfoById(@PathVariable("clubId") Integer clubId){
        return groupInfoService.queryGroupInfoById(clubId);
    }

    @PostMapping("insert")
    public int insertGroupInfo(@RequestBody GroupInfo groupInfo){
        return groupInfoService.insertGroupInfo(groupInfo);
    }

    @DeleteMapping("delete/{clubId}")
    public int deleteGroupInfoById(@PathVariable("clubId") Integer clubId){
        return groupInfoService.deleteGroupInfoById(clubId);
    }

    @PutMapping("update")
    public int updateGroupInfoById(@RequestBody GroupInfo groupInfo){
        return groupInfoService.updateGroupInfoById(groupInfo);
    }

}
