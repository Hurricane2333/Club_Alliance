package com.example.club.entity;

import com.example.club.enums.ClubPostStatus;
import com.example.club.enums.ClubPostVisibility;
import com.example.club.enums.ClubStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class GroupPost {

    private Integer postId;
    private Integer clubId;
    private Integer userId;
    private String title;
    private String content;
    private ClubPostVisibility visibility;
    private ClubPostStatus status;
    private Integer commentCount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedAt;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
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

    public ClubPostVisibility getVisibility() {
        return visibility;
    }

    public void setVisibility(ClubPostVisibility visibility) {
        this.visibility = visibility;
    }

    public ClubPostStatus getStatus() {
        return status;
    }

    public void setStatus(ClubPostStatus status) {
        this.status = status;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "GroupPost{" +
                "postId=" + postId +
                ", clubId=" + clubId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", visibility=" + visibility +
                ", status=" + status +
                ", commentCount=" + commentCount +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
