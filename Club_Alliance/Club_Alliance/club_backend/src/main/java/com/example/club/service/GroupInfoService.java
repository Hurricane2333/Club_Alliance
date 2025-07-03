package com.example.club.service;

import com.example.club.entity.GroupInfo;
import com.example.club.enums.ClubCategory;

import java.util.List;

public interface GroupInfoService {

    List<GroupInfo> queryAllGroupInfo();
    List<GroupInfo> queryGroupInfoByName(String clubName);
    List<GroupInfo> queryGroupInfoByCategory(ClubCategory category);
    GroupInfo queryGroupInfoById(Integer clubId);
    int insertGroupInfo(GroupInfo groupInfo);
    int deleteGroupInfoById(Integer clubId);
    int updateGroupInfoById(GroupInfo groupInfo);

}
