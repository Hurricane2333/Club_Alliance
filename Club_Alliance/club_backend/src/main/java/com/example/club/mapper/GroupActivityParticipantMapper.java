package com.example.club.mapper;

import com.example.club.entity.GroupActivityParticipant;
import com.example.club.enums.ActivityParticipant;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GroupActivityParticipantMapper {

    @Select("select * from activity_participant")
    List<GroupActivityParticipant> queryAllGroupActivityParticipant();

    @Select("select * from activity_participant where participation_id = #{participationId}")
    GroupActivityParticipant queryGroupActivityParticipantById(Integer participationId);

    @Select("select * from activity_participant where activity_id = #{activityId}")
    List<GroupActivityParticipant> queryGroupActivityParticipantByActivityId(Integer activityId);

    @Select("select * from activity_participant where user_id = #{userId}")
    List<GroupActivityParticipant> queryGroupActivityParticipantByUserId(Integer userId);

    @Select("select * from activity_participant where status = #{status}")
    List<GroupActivityParticipant> queryGroupActivityParticipantByStatus(ActivityParticipant status);

    @Insert("insert into activity_participant (activity_id, user_id) values(#{activityId}, #{userId})")
    int insertGroupActivityParticipant(GroupActivityParticipant groupActivityParticipant);

    @Delete("delete from activity_participant where participation_id = #{participationId}")
    int deleteGroupActivityParticipantById(Integer participationId);

    @Update("update activity_participant set status = #{status} where participation_id = #{participationId}")
    int updateGroupActivityParticipantById(GroupActivityParticipant groupActivityParticipant);

}
