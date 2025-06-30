package com.example.club.service;

import com.example.club.entity.ClubActivity;
import com.example.club.mapper.ClubActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubActivityService {
    @Autowired
    private ClubActivityMapper clubActivityMapper;

    public List<ClubActivity> getRecentActivities(int limit) {
        return clubActivityMapper.selectRecentActivities(limit);
    }
}