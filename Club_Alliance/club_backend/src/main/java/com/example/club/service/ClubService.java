package com.example.club.service;

import com.example.club.entity.Club;
import com.example.club.mapper.ClubMapper;
import org.springframework.stereotype.Service;

@Service
public class ClubService {

    private final ClubMapper clubMapper;

    public ClubService(ClubMapper clubMapper) {
        this.clubMapper = clubMapper;
    }

    public Club selectById(int id) {
        return clubMapper.selectById(id);
    }
}