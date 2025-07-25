package com.example.club.service;

import com.example.club.entity.ActivityParticipant;
import com.example.club.enums.ParticipantStatus;
import com.example.club.mapper.ActivityParticipantMapper;

import java.util.List;
import com.example.club.entity.ClubActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ActivityParticipantService {
    @Autowired
    private ActivityParticipantMapper mapper;

    public boolean joinActivity(Integer activityId, Integer userId) {
        ActivityParticipant exist = mapper.selectByActivityIdAndUserId(activityId, userId);
        if (exist != null) {
            return false; // 已申请或已参加
        }
        ActivityParticipant participant = new ActivityParticipant();
        participant.setActivityId(activityId);
        participant.setUserId(userId);
        participant.setStatus(ParticipantStatus.PENDING); // 默认PENDING
        mapper.insertParticipant(participant);
        return true;
    }

     // 返回 List<Map<String, Object>>
    public java.util.List<java.util.Map<String, Object>> getJoinedActivities(Integer userId) {
        return mapper.selectJoinedActivitiesByUserId(userId);
    }
}