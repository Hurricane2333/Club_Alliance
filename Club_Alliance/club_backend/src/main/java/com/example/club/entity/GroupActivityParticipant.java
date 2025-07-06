package com.example.club.entity;

import com.example.club.enums.ActivityParticipant;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class GroupActivityParticipant {

    private Integer participationId;
    private Integer activityId;
    private Integer userId;
    private ActivityParticipant status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date applyTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date reviewTime;

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

    public ActivityParticipant getStatus() {
        return status;
    }

    public void setStatus(ActivityParticipant status) {
        this.status = status;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    @Override
    public String toString() {
        return "GroupActivityParticipant{" +
                "participationId=" + participationId +
                ", activityId=" + activityId +
                ", userId=" + userId +
                ", status=" + status +
                ", applyTime=" + applyTime +
                ", reviewTime=" + reviewTime +
                '}';
    }

}
