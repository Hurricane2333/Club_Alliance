package com.example.club.mapper;

import com.example.club.entity.Club;
import com.example.club.enums.ClubStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ClubMapper {
        @Select("SELECT " +
                "c.club_id, c.club_name, c.description, c.category, c.icon, " +
                "c.president_id, c.requirements, c.favorite_count, c.current_members, " +
                "c.status, c.created_at, u.stu_name as president_name " +  // 显式列出字段
                "FROM club c LEFT JOIN user u ON c.president_id = u.user_id " +  // 改用LEFT JOIN
                "WHERE c.status = #{status}"+
                "ORDER BY c.favorite_count DESC, c.current_members DESC " +
                "LIMIT #{limit}")
        List<Club> selectRecommendedClubs(@Param("limit") int limit ,@Param("status") ClubStatus status);

        @Select("SELECT c.*, u.stu_name as president_name " +
                "FROM club c JOIN user u ON c.president_id = u.user_id " +
                "WHERE c.club_id = #{clubId}")
        Club selectClubById(@Param("clubId") Integer clubId);

        @Update("UPDATE club SET favorite_count = favorite_count + 1 WHERE club_id = #{clubId}")
        int increaseFavoriteCount(@Param("clubId") Integer clubId);

        @Select("SELECT * FROM club WHERE status = 'approved' " +
                "ORDER BY created_at DESC " +
                "LIMIT #{limit}")
        List<Club> selectNewClubs(@Param("limit") int limit);
}