package com.example.club.entity;

import lombok.Data;
import java.util.Date;

import com.example.club.enums.ClubActivityStatus;

@Data
public class ClubActivity {
    private Integer activityId;
    private Integer clubId;
    private Integer userId;
    private String title;
    private String content;
    private Date startTime;
    private Date endTime;
    private String location;
    private ClubActivityStatus status;
    private Integer currentParticipants;
    private Integer maxParticipants;
    private Date createdAt;

    // 关联信息
    private String clubName;
    private String clubIcon;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ClubActivityStatus getClubactivitystatus() {
        return status;
    }

    public void setClubactivitystatus(ClubActivityStatus status) {
        this.status = status;
    }

    public Integer getCurrentParticipants() {
        return currentParticipants;
    }

    public void setCurrentParticipants(Integer currentParticipants) {
        this.currentParticipants = currentParticipants;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubIcon() {
        return clubIcon;
    }

    public void setClubIcon(String clubIcon) {
        this.clubIcon = clubIcon;
    }

    @Override
    public String toString() {
        return "ClubActivity{" +
                "activityId=" + activityId +
                ", clubId=" + clubId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", location='" + location + '\'' +
                ", status=" + status +
                ", currentParticipants=" + currentParticipants +
                ", maxParticipants=" + maxParticipants +
                ", createdAt=" + createdAt +
                ", clubName='" + clubName + '\'' +
                ", clubIcon='" + clubIcon + '\'' +
                '}';
    }
}
