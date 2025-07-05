package com.example.club.service;

import com.example.club.entity.GroupMember;
import com.example.club.enums.ClubMemberStatus;
import com.example.club.mapper.GroupMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupMemberServiceImpl implements GroupMemberService {

    @Autowired
    private GroupMemberMapper groupMemberMapper;

    @Override
    public List<GroupMember> queryAllGroupMember() {
        return groupMemberMapper.queryAllGroupMember();
    }

    @Override
    public GroupMember queryGroupMemberById(Integer relationId) {
        return groupMemberMapper.queryGroupMemberById(relationId);
    }

    @Override
    public List<GroupMember> queryGroupMemberByUserId(Integer userId) {
        return groupMemberMapper.queryGroupMemberByUserId(userId);
    }

    @Override
    public List<GroupMember> queryGroupMemberByClubId(Integer clubId) {
        return groupMemberMapper.queryGroupMemberByClubId(clubId);
    }

    @Override
    public List<GroupMember> queryGroupMemberByStatus(ClubMemberStatus status) {
        return groupMemberMapper.queryGroupMemberByStatus(status);
    }

    @Override
    public int insertGroupMember(GroupMember groupMember) {
        return groupMemberMapper.insertGroupMember(groupMember);
    }

    @Override
    public int deleteGroupMemberById(Integer relationId) {
        return groupMemberMapper.deleteGroupMemberById(relationId);
    }

    @Override
    public int updateGroupMemberById(GroupMember groupMember) {
        return groupMemberMapper.updateGroupMemberById(groupMember);
    }

    @Override
    public List<GroupMember> queryGroupMemberByClubIdAndStatus(Integer clubId, ClubMemberStatus status) {
        return groupMemberMapper.queryGroupMemberByClubIdAndStatus(clubId, status);
    }

}
