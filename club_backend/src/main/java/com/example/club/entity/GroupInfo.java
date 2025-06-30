package com.example.club.entity;

import com.example.club.enums.ClubCategory;
import com.example.club.enums.ClubStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class GroupInfo {

    private Integer clubId;
    private String clubName;
    private String description;
    private ClubCategory category;
    private String icon;
    private Integer presidentId;
    private String requirements;
    private Integer favoriteCount;
    private Integer currentMembers;
    private ClubStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdAt;

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ClubCategory getCategory() {
        return category;
    }

    public void setCategory(ClubCategory category) {
        this.category = category;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getPresidentId() {
        return presidentId;
    }

    public void setPresidentId(Integer presidentId) {
        this.presidentId = presidentId;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public Integer getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(Integer favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public Integer getCurrentMembers() {
        return currentMembers;
    }

    public void setCurrentMembers(Integer currentMembers) {
        this.currentMembers = currentMembers;
    }

    public ClubStatus getStatus() {
        return status;
    }

    public void setStatus(ClubStatus status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "GroupInfo{" +
                "clubId=" + clubId +
                ", clubName='" + clubName + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", icon='" + icon + '\'' +
                ", presidentId=" + presidentId +
                ", requirements='" + requirements + '\'' +
                ", favoriteCount=" + favoriteCount +
                ", currentMembers=" + currentMembers +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }

}
