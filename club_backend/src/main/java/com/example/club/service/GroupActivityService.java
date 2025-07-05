package com.example.club.service;

import com.example.club.entity.GroupActivity;
import com.example.club.enums.ClubActivityStatus;

import java.util.List;

public interface GroupActivityService {

    List<GroupActivity> queryAllGroupActivity();
    GroupActivity queryGroupActivityById(Integer activityId);
    List<GroupActivity> queryGroupActivityByClubId(Integer clubId);
    List<GroupActivity> queryGroupActivityByUserId(Integer userId);
    List<GroupActivity> queryGroupActivityByTitle(String title);
    List<GroupActivity> queryGroupActivityByStatus(ClubActivityStatus status);
    int insertGroupActivity(GroupActivity groupActivity);
    int deleteGroupActivityById(Integer activityId);
    int updateGroupActivityById(GroupActivity groupActivity);

}
