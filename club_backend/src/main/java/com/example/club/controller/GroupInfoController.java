package com.example.club.controller;

import com.example.club.entity.ClubMember;
import com.example.club.entity.GroupInfo;
import com.example.club.enums.ClubCategory;
import com.example.club.enums.ClubMemberStatus;
import com.example.club.mapper.ApplyMapper;
import com.example.club.service.GroupInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("group")
public class GroupInfoController {

    @Autowired
    private GroupInfoService groupInfoService;

    @Autowired
    private ApplyMapper applyMapper;

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
   

    //检查用户是否已加入社团--hzs
    @GetMapping("/check")
    public Map<String, Object> checkJoined(@RequestParam Integer userId, @RequestParam Integer clubId) {
    Map<String, Object> result = new HashMap<>();
    ClubMember member = applyMapper.selectByUserIdAndClubId(userId, clubId);
    result.put("joined", member != null && member.getStatus() == ClubMemberStatus.APPROVED);
    return result;
    }
}

