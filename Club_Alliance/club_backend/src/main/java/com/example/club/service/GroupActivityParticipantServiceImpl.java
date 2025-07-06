package com.example.club.service;

import com.example.club.entity.GroupActivityParticipant;
import com.example.club.enums.ActivityParticipant;
import com.example.club.mapper.GroupActivityParticipantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupActivityParticipantServiceImpl implements GroupActivityParticipantService {

    @Autowired
    private GroupActivityParticipantMapper groupActivityParticipantMapper;

    @Override
    public List<GroupActivityParticipant> queryAllGroupActivityParticipant() {
        return groupActivityParticipantMapper.queryAllGroupActivityParticipant();
    }

    @Override
    public GroupActivityParticipant queryGroupActivityParticipantById(Integer participationId) {
        return groupActivityParticipantMapper.queryGroupActivityParticipantById(participationId);
    }

    @Override
    public List<GroupActivityParticipant> queryGroupActivityParticipantByActivityId(Integer activityId) {
        return groupActivityParticipantMapper.queryGroupActivityParticipantByActivityId(activityId);
    }

    @Override
    public List<GroupActivityParticipant> queryGroupActivityParticipantByUserId(Integer userId) {
        return groupActivityParticipantMapper.queryGroupActivityParticipantByUserId(userId);
    }

    @Override
    public List<GroupActivityParticipant> queryGroupActivityParticipantByStatus(ActivityParticipant status) {
        return groupActivityParticipantMapper.queryGroupActivityParticipantByStatus(status);
    }

    @Override
    public int insertGroupActivityParticipant(GroupActivityParticipant groupActivityParticipant) {
        return groupActivityParticipantMapper.insertGroupActivityParticipant(groupActivityParticipant);
    }

    @Override
    public int deleteGroupActivityParticipantById(Integer participationId) {
        return groupActivityParticipantMapper.deleteGroupActivityParticipantById(participationId);
    }

    @Override
    public int updateGroupActivityParticipantById(GroupActivityParticipant groupActivityParticipant) {
        return groupActivityParticipantMapper.updateGroupActivityParticipantById(groupActivityParticipant);
    }

}
