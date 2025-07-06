package com.example.club.mapper;

import com.example.club.entity.FavoriteClub;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface FavoriteClubMapper {
    FavoriteClub selectByFavoriteId(int id);

    List<FavoriteClub> selectByUserId(int userId);

    @Insert("INSERT INTO favorite_club (user_id, club_id) VALUES (#{userId}, #{clubId})")
    @Options(useGeneratedKeys = true, keyProperty = "favoriteId", keyColumn = "favorite_id")
    int insertFavorite(FavoriteClub favoriteClub);
}
