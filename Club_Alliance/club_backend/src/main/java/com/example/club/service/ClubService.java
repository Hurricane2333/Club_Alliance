package com.example.club.service;

import com.example.club.entity.Club;
import com.example.club.enums.ClubStatus;
import com.example.club.mapper.ClubMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.club.entity.Result;
import java.util.List;

@Service
public class ClubService {
    @Autowired
    private  ClubMapper clubMapper;


    /*public ClubService(ClubMapper clubMapper) {
        this.clubMapper = clubMapper;
    }*/


    public List<Club> getHomeRecommendedClubs() {
        List<Club> clubs = clubMapper.selectRecommendedClubs(2, ClubStatus.APPROVED);
        return clubs;
    }

    public Club getClubById(Integer clubId) {
        return clubMapper.selectClubById(clubId);
    }

    // 新增方法：增加社团收藏数
    /*public boolean increaseFavoriteCount(Integer clubId) {
        try {
            int affectedRows = clubMapper.increaseFavoriteCount(clubId);
            return affectedRows > 0;
        } catch (Exception e) {
            throw new RuntimeException("更新收藏数失败", e);
        }
    }*/
}
