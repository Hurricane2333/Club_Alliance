package com.example.club.mapper;

import com.example.club.entity.ClubMember;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ApplyMapper {
    @Insert("INSERT INTO club_member (user_id, club_id, status, apply_reason, apply_time) " +
        "VALUES (#{userId}, #{clubId}, #{status}, #{applyReason}, NOW())")
    int insertApplication(ClubMember clubMember);

    @Select("SELECT * FROM club_member WHERE user_id = #{userId} AND club_id = #{clubId}")
    @Results({
        @Result(property = "status", column = "status", javaType = com.example.club.enums.ClubMemberStatus.class)
    })
    ClubMember selectByUserIdAndClubId(@Param("userId") Integer userId, @Param("clubId") Integer clubId);
    
    @Select("SELECT c.club_id AS id, c.club_name AS name, c.icon AS logo, cm.apply_time AS joinDate " +
            "FROM club_member cm " +
            "JOIN club c ON cm.club_id = c.club_id " +
            "WHERE cm.user_id = #{userId} AND cm.status = 'APPROVED'")
    java.util.List<java.util.Map<String, Object>> selectJoinedClubsByUserId(@Param("userId") Integer userId);

    
}