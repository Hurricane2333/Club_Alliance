package com.example.club.service;

import com.example.club.entity.GroupInfo;
import com.example.club.enums.ClubCategory;
import com.example.club.enums.ClubStatus;
import com.example.club.mapper.GroupInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupInfoServiceImpl implements GroupInfoService {

    @Autowired
    private GroupInfoMapper groupInfoMapper;

    @Override
    public List<GroupInfo> queryAllGroupInfo() {
        return groupInfoMapper.queryAllGroupInfo();
    }

    @Override
    public List<GroupInfo> queryGroupInfoByName(String clubName) {
        return groupInfoMapper.queryGroupInfoByName(clubName);
    }

    @Override
    public List<GroupInfo> queryGroupInfoByCategory(ClubCategory category) {
        return groupInfoMapper.queryGroupInfoByCategory(category);
    }

    @Override
    public GroupInfo queryGroupInfoById(Integer clubId) {
        return groupInfoMapper.queryGroupInfoById(clubId);
    }

    @Override
    public int insertGroupInfo(GroupInfo groupInfo) {
        return groupInfoMapper.insertGroupInfo(groupInfo);
    }

    @Override
    public int deleteGroupInfoById(Integer clubId) {
        return groupInfoMapper.deleteGroupInfoById(clubId);
    }

    @Override
    public int updateGroupInfoById(GroupInfo groupInfo) {
        return groupInfoMapper.updateGroupInfoById(groupInfo);
    }

    @Override
    public List<GroupInfo> queryGroupInfoByStatus(ClubStatus status) {
        return groupInfoMapper.queryGroupInfoByStatus(status);
    }
}
