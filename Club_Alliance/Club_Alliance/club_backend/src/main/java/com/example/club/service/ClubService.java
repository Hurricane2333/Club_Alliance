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

    public ClubService(ClubMapper clubMapper) {
        this.clubMapper = clubMapper;
    }
    

    public List<Club> getHomeRecommendedClubs() {
        List<Club> clubs = clubMapper.selectRecommendedClubs(2, ClubStatus.APPROVED);
        return clubs;
    }

    public List<Club> getAllApprovedClubs(ClubStatus approved) {
        return clubMapper.selectAllApprovedClubs(ClubStatus.APPROVED);
    }

    public Club selectById(int id) {
        return clubMapper.selectById(id);
    }
}
