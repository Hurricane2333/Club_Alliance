package com.admin.club.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * club
 * @author 
 */
public class AdminClub implements Serializable {
    /**
     * 社团id
     */
    private Integer clubId;

    /**
     * 社团名字
     */
    private String clubName;

    /**
     * 社团类别
     */
    private String category;

    /**
     * 社团图标URL
     */
    private String icon;

    /**
     * 负责人ID
     */
    private Integer presidentId;

    /**
     * 收藏人数
     */
    private Integer favoriteCount;

    /**
     * 现有人数
     */
    private Integer currentMembers;

    /**
     * 审核状态
     */
    private String status;

    /**
     * 社团创建时间
     */
    private Date createdAt;

    private static final long serialVersionUID = 1L;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AdminClub other = (AdminClub) that;
        return (this.getClubId() == null ? other.getClubId() == null : this.getClubId().equals(other.getClubId()))
            && (this.getClubName() == null ? other.getClubName() == null : this.getClubName().equals(other.getClubName()))
            && (this.getCategory() == null ? other.getCategory() == null : this.getCategory().equals(other.getCategory()))
            && (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()))
            && (this.getPresidentId() == null ? other.getPresidentId() == null : this.getPresidentId().equals(other.getPresidentId()))
            && (this.getFavoriteCount() == null ? other.getFavoriteCount() == null : this.getFavoriteCount().equals(other.getFavoriteCount()))
            && (this.getCurrentMembers() == null ? other.getCurrentMembers() == null : this.getCurrentMembers().equals(other.getCurrentMembers()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getClubId() == null) ? 0 : getClubId().hashCode());
        result = prime * result + ((getClubName() == null) ? 0 : getClubName().hashCode());
        result = prime * result + ((getCategory() == null) ? 0 : getCategory().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        result = prime * result + ((getPresidentId() == null) ? 0 : getPresidentId().hashCode());
        result = prime * result + ((getFavoriteCount() == null) ? 0 : getFavoriteCount().hashCode());
        result = prime * result + ((getCurrentMembers() == null) ? 0 : getCurrentMembers().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", clubId=").append(clubId);
        sb.append(", clubName=").append(clubName);
        sb.append(", category=").append(category);
        sb.append(", icon=").append(icon);
        sb.append(", presidentId=").append(presidentId);
        sb.append(", favoriteCount=").append(favoriteCount);
        sb.append(", currentMembers=").append(currentMembers);
        sb.append(", status=").append(status);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}