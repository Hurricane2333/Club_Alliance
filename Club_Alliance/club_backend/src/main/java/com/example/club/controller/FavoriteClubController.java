package com.example.club.controller;

import com.example.club.entity.Result;
import com.example.club.entity.Club;
import com.example.club.service.FavoriteClubService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/favorite")
public class FavoriteClubController {

    private final FavoriteClubService favoriteClubService;

    public FavoriteClubController(FavoriteClubService favoriteClubService) {
        this.favoriteClubService = favoriteClubService;
    }

    @GetMapping("/list")
    public Result getFavoriteClubsByUserId(@RequestParam Integer userId) {
        List<Club> favoriteClubs = favoriteClubService.getFavoriteClubsByUserId(userId);
        return Result.success(favoriteClubs);
    }

    @PostMapping("/add")
    public Result addFavorite(@RequestParam Integer userId, @RequestParam Integer clubId) {
        return favoriteClubService.addFavorite(userId, clubId);
    }
}
