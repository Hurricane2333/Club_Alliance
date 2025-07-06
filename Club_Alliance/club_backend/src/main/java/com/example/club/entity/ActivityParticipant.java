package com.example.club.entity;

import com.example.club.enums.ParticipantStatus;
import java.util.Date;

public class ActivityParticipant {
    private Integer participationId;
    private Integer activityId;
    private Integer userId;
    private ParticipantStatus status;
    private Date applyTime;
    private Date reviewTime;
    // getter/setter略
    public Integer getParticipationId() {
        return participationId;
    }
    public void setParticipationId(Integer participationId) {
        this.participationId = participationId;
    }
    public Integer getActivityId() {
        return activityId;
    }
    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public ParticipantStatus getStatus() {
        return status;
    }
    public void setStatus(ParticipantStatus status) {
        this.status = status;
    }
}
