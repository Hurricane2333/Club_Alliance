package com.example.club.service;

import com.example.club.entity.GroupActivityParticipant;
import com.example.club.enums.ActivityParticipant;

import java.util.List;

public interface GroupActivityParticipantService {

    List<GroupActivityParticipant> queryAllGroupActivityParticipant();
    GroupActivityParticipant queryGroupActivityParticipantById(Integer participationId);
    List<GroupActivityParticipant> queryGroupActivityParticipantByActivityId(Integer activityId);
    List<GroupActivityParticipant> queryGroupActivityParticipantByUserId(Integer userId);
    List<GroupActivityParticipant> queryGroupActivityParticipantByStatus(ActivityParticipant status);
    int insertGroupActivityParticipant(GroupActivityParticipant groupActivityParticipant);
    int deleteGroupActivityParticipantById(Integer participationId);
    int updateGroupActivityParticipantById(GroupActivityParticipant groupActivityParticipant);

}
