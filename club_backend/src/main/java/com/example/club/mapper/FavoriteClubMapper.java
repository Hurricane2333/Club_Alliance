package com.example.club.mapper;

import com.example.club.entity.FavoriteClub;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FavoriteClubMapper {
    FavoriteClub selectByFavoriteId(int id);

    List<FavoriteClub> selectByUserId(int userId);
}