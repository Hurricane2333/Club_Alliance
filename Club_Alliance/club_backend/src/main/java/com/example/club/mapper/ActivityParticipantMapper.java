package com.example.club.mapper;

import com.example.club.entity.ActivityParticipant;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.example.club.entity.ClubActivity;

@Mapper
public interface ActivityParticipantMapper {
    @Insert("INSERT INTO activity_participant (activity_id, user_id, status, apply_time) " +
            "VALUES (#{activityId}, #{userId}, #{status}, NOW())")
    int insertParticipant(ActivityParticipant participant);

    @Select("SELECT * FROM activity_participant WHERE activity_id = #{activityId} AND user_id = #{userId}")
    ActivityParticipant selectByActivityIdAndUserId(@Param("activityId") Integer activityId, @Param("userId") Integer userId);

     // 查询用户参与的所有活动及活动详情
    @Select("SELECT a.activity_id AS id, a.user_id,a.title, a.start_time, a.end_time, a.location, a.content, a.created_at, " +
        "c.club_id AS clubId, c.club_name AS clubName, c.icon AS clubIcon " +
        "FROM activity_participant ap " +
        "JOIN club_activity a ON ap.activity_id = a.activity_id " +
        "JOIN club c ON a.club_id = c.club_id " +
        "WHERE ap.user_id = #{userId} AND ap.status = 'APPROVED'")
    java.util.List<java.util.Map<String, Object>> selectJoinedActivitiesByUserId(@Param("userId") Integer userId);

}