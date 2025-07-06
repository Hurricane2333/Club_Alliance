package com.example.club.service;

import com.example.club.entity.GroupMember;
import com.example.club.enums.ClubMemberStatus;

import java.util.List;

public interface GroupMemberService {

    List<GroupMember> queryAllGroupMember();
    GroupMember queryGroupMemberById(Integer relationId);
    List<GroupMember> queryGroupMemberByUserId(Integer userId);
    List<GroupMember> queryGroupMemberByClubId(Integer clubId);
    List<GroupMember> queryGroupMemberByStatus(ClubMemberStatus status);
    int insertGroupMember(GroupMember groupMember);
    int deleteGroupMemberById(Integer relationId);
    int updateGroupMemberById(GroupMember groupMember);
    List<GroupMember> queryGroupMemberByClubIdAndStatus(Integer clubId, ClubMemberStatus status);
    GroupMember queryGroupMemberStatusByUserIdAndClubId(Integer userId, Integer clubId);

}
