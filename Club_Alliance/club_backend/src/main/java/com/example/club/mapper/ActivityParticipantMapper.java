package com.example.club.mapper;

import com.example.club.entity.ActivityParticipant;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ActivityParticipantMapper {
    @Insert("INSERT INTO activity_participant (activity_id, user_id, status, apply_time) " +
            "VALUES (#{activityId}, #{userId}, #{status}, NOW())")
    int insertParticipant(ActivityParticipant participant);

    @Select("SELECT * FROM activity_participant WHERE activity_id = #{activityId} AND user_id = #{userId}")
    ActivityParticipant selectByActivityIdAndUserId(@Param("activityId") Integer activityId, @Param("userId") Integer userId);
}