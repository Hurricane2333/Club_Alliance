package com.example.club.service;

import com.example.club.entity.ActivityResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminClubActivityService {
    List<ActivityResponse> allClubActivity();
    boolean passActivity(int activityId);
    boolean refuseActivity(int activityId);
    boolean deleteActivity(int activityId);
    List<ActivityResponse> searchActivity(String name);
}
