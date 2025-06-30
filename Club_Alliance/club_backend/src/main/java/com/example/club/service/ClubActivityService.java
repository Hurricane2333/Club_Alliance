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

    public List<ClubActivity> getRecentActivities() {
        List<ClubActivity> clubActivities=clubActivityMapper.selectRecentActivities(2);
        return clubActivities;
    }
    public List<ClubActivity> getAllActivities() {
        List<ClubActivity> AllclubActivities = clubActivityMapper.selectRecentActivities(100);
        return AllclubActivities;
    }
}