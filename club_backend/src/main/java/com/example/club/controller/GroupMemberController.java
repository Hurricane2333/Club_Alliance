package com.example.club.controller;

import com.example.club.entity.GroupMember;
import com.example.club.enums.ClubMemberStatus;
import com.example.club.service.GroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("group/member")
public class GroupMemberController {

    @Autowired
    private GroupMemberService groupMemberService;

    @GetMapping("selectAll")
    private List<GroupMember> queryAllMembers() {
        return groupMemberService.queryAllGroupMember();
    }

    @GetMapping("selectId/{relationId}")
    private GroupMember queryMemberById(@PathVariable("relationId") Integer relationId) {
        return groupMemberService.queryGroupMemberById(relationId);
    }

    @GetMapping("selectUserId/{userId}")
    private List<GroupMember> queryMemberByUserId(@PathVariable("userId") Integer userId) {
        return groupMemberService.queryGroupMemberByUserId(userId);
    }

    @GetMapping("selectClubId/{clubId}")
    private List<GroupMember> queryMemberByClubId(@PathVariable("clubId") Integer clubId) {
        return groupMemberService.queryGroupMemberByClubId(clubId);
    }

    @GetMapping("selectStatus/{status}")
    private List<GroupMember> queryMemberByStatus(@PathVariable("status") ClubMemberStatus status) {
        return groupMemberService.queryGroupMemberByStatus(status);
    }

    @PostMapping("insert")
    private int insertGroupMember(@RequestBody GroupMember groupMember) {
        return groupMemberService.insertGroupMember(groupMember);
    }

    @DeleteMapping("delete/{relationId}")
    private int deleteGroupMemberById(@PathVariable("relationId") Integer relationId) {
        return groupMemberService.deleteGroupMemberById(relationId);
    }

    @PutMapping("update")
    private int updateGroupMemberById(@RequestBody GroupMember groupMember) {
        return groupMemberService.updateGroupMemberById(groupMember);
    }

    @GetMapping("selectClubIdAndStatus/{clubId}/{status}")
    private List<GroupMember> queryGroupMemberByClubIdAndStatus(@PathVariable("clubId") Integer clubId, @PathVariable("status") ClubMemberStatus status) {
        return groupMemberService.queryGroupMemberByClubIdAndStatus(clubId, status);
    }

}
