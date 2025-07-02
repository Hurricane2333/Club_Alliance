package com.example.club.service;

import com.example.club.entity.Club;
import com.example.club.entity.FavoriteClub;
import com.example.club.mapper.FavoriteClubMapper;
import org.springframework.stereotype.Service;

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
}