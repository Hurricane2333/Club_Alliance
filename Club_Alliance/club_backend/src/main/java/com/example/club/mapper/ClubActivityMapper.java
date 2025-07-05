package com.example.club.mapper;

import com.example.club.entity.ClubActivity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClubActivityMapper {
    @Select("SELECT a.*, c.club_name, c.icon as club_icon " +
            "FROM club_activity a " +
            "LEFT JOIN club c ON a.club_id = c.club_id " +
            "WHERE a.status = 'ACTIVE' " +
            "ORDER BY a.start_time DESC " +
            "LIMIT #{limit}")
    List<ClubActivity> selectRecentActivities(@Param("limit") int limit);
    @Select("SELECT a.*, c.club_name, c.icon as club_icon " +
            "FROM club_activity a " +
            "LEFT JOIN club c ON a.club_id = c.club_id " +
            "WHERE a.status = 'ACTIVE' " +
            "ORDER BY a.start_time DESC")
    List<ClubActivity> selectAllActivities();

    @Select("SELECT a.*, c.club_name, c.icon as club_icon " +
            "FROM club_activity a " +
            "LEFT JOIN club c ON a.club_id = c.club_id " +
            "WHERE a.activity_id = #{id} LIMIT 1")
    ClubActivity selectActivityDetailById(@Param("id") Integer id);
}
