package com.example.club.service;

import com.example.club.entity.Club;
import com.example.club.entity.FavoriteClub;
import com.example.club.mapper.FavoriteClubMapper;
import org.springframework.stereotype.Service;
import com.example.club.entity.Result;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteClubService {

    private final FavoriteClubMapper favoriteClubMapper;
    private final ClubService clubService;

    public FavoriteClubService(FavoriteClubMapper favoriteClubMapper, ClubService clubService) {
        this.favoriteClubMapper = favoriteClubMapper;
        this.clubService = clubService;
    }

    public FavoriteClub selectByFavoriteId(int id) {
        return favoriteClubMapper.selectByFavoriteId(id);
    }

    public List<Club> getFavoriteClubsByUserId(int userId) {
        List<FavoriteClub> favoriteClubs = favoriteClubMapper.selectByUserId(userId);
        return favoriteClubs.stream()
                .map(favoriteClub -> clubService.selectById(favoriteClub.getClubId()))
                .collect(Collectors.toList());
    }

    public Result addFavorite(int userId, int clubId) {
        // 检查是否已收藏
        List<FavoriteClub> existing = favoriteClubMapper.selectByUserId(userId).stream()
                .filter(fc -> fc.getClubId() == clubId)
                .collect(Collectors.toList());

        if (!existing.isEmpty()) {
            return Result.error("已收藏过该社团");
        }

        FavoriteClub favorite = new FavoriteClub();
        favorite.setUserId(userId);
        favorite.setClubId(clubId);
        int result = favoriteClubMapper.insertFavorite(favorite);
        return result > 0 ? Result.success("收藏成功") : Result.error("收藏失败");
    }

}
