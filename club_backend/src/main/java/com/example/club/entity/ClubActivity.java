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
    private ClubActivityStatus clubactivitystatus;
    private Integer currentParticipants;
    private Integer maxParticipants;
    private Date createdAt;

    // 关联信息
    private String clubName;
    private String clubIcon;
}