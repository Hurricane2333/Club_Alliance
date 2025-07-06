package com.example.club.mapper;

import com.example.club.entity.GroupActivity;
import com.example.club.enums.ClubActivityStatus;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GroupActivityMapper {

    @Select("select * from club_activity")
    List<GroupActivity> queryAllGroupActivity();

    @Select("select * from club_activity where activity_id = #{activityId}")
    GroupActivity queryGroupActivityById(Integer activityId);

    @Select("select * from club_activity where club_id = #{clubId}")
    List<GroupActivity> queryGroupActivityByClubId(Integer clubId);

    @Select("select * from club_activity where user_id = #{userId}")
    List<GroupActivity> queryGroupActivityByUserId(Integer userId);

    @Select("select * from club_activity where title = #{title}")
    List<GroupActivity> queryGroupActivityByTitle(String title);

    @Select("select * from club_activity where status = #{status}")
    List<GroupActivity> queryGroupActivityByStatus(ClubActivityStatus status);

    @Insert("insert into club_activity (club_id, user_id, title, content, start_time, end_time, location, max_participants) values(#{clubId}, #{userId}, #{title}, #{content}, #{startTime}, #{endTime}, #{location}, #{maxParticipants})")
    int insertGroupActivity(GroupActivity groupActivity);

    @Delete("delete from club_activity where activity_id = #{activityId}")
    int deleteGroupActivityById(Integer activityId);

    @Update("update club_activity set club_id = #{clubId}, user_id = #{userId}, title = #{title}, content = #{content}, start_time = #{startTime}, end_time = #{endTime}, location = #{location}, max_participants = #{maxParticipants}, status = #{status} where activity_id = #{activityId}")
    int updateGroupActivityById(GroupActivity groupActivity);

}
