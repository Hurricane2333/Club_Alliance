package com.example.club.service;

import com.example.club.entity.GroupActivity;
import com.example.club.enums.ClubActivityStatus;
import com.example.club.mapper.GroupActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupActivityServiceImpl implements GroupActivityService {

    @Autowired
    private GroupActivityMapper groupActivityMapper;

    @Override
    public List<GroupActivity> queryAllGroupActivity() {
        return groupActivityMapper.queryAllGroupActivity();
    }

    @Override
    public GroupActivity queryGroupActivityById(Integer activityId) {
        return groupActivityMapper.queryGroupActivityById(activityId);
    }

    @Override
    public List<GroupActivity> queryGroupActivityByClubId(Integer clubId) {
        return groupActivityMapper.queryGroupActivityByClubId(clubId);
    }

    @Override
    public List<GroupActivity> queryGroupActivityByUserId(Integer userId) {
        return groupActivityMapper.queryGroupActivityByUserId(userId);
    }

    @Override
    public List<GroupActivity> queryGroupActivityByTitle(String title) {
        return groupActivityMapper.queryGroupActivityByTitle(title);
    }

    @Override
    public List<GroupActivity> queryGroupActivityByStatus(ClubActivityStatus status) {
        return groupActivityMapper.queryGroupActivityByStatus(status);
    }

    @Override
    public int insertGroupActivity(GroupActivity groupActivity) {
        return groupActivityMapper.insertGroupActivity(groupActivity);
    }

    @Override
    public int deleteGroupActivityById(Integer activityId) {
        return groupActivityMapper.deleteGroupActivityById(activityId);
    }

    @Override
    public int updateGroupActivityById(GroupActivity groupActivity) {
        return groupActivityMapper.updateGroupActivityById(groupActivity);
    }
}
