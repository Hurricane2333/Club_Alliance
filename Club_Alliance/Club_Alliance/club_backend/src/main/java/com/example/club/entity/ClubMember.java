package com.example.club.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import com.example.club.enums.ClubMemberStatus;

public class ClubMember {
    private Integer relationId;
    private Integer userId;
    private Integer clubId;
    private ClubMemberStatus status; 
    private String applyReason;
    private LocalDateTime applyTime;
    private LocalDateTime reviewTime;
    private String rejectReason;

    // getters and setters

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

    public LocalDateTime getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(LocalDateTime applyTime) {
        this.applyTime = applyTime;
    }

    public LocalDateTime getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(LocalDateTime reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
}