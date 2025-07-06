package com.example.club.entity;

import com.example.club.enums.ClubMemberStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class GroupMember {

    private Integer relationId;
    private Integer userId;
    private Integer clubId;
    private ClubMemberStatus status;
    private String applyReason;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date applyTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date reviewTime;
    private String rejectReason;

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public ClubMemberStatus getStatus() {
        return status;
    }

    public void setStatus(ClubMemberStatus status) {
        this.status = status;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
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

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    @Override
    public String toString() {
        return "GroupMember{" +
                "relationId=" + relationId +
                ", userId=" + userId +
                ", clubId=" + clubId +
                ", status=" + status +
                ", applyReason='" + applyReason + '\'' +
                ", applyTime=" + applyTime +
                ", reviewTime=" + reviewTime +
                ", rejectReason='" + rejectReason + '\'' +
                '}';
    }

}
