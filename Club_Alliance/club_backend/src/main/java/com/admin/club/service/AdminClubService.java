package com.admin.club.service;

import com.admin.club.entity.AdminClub;
import com.admin.club.entity.ClubResponse;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface AdminClubService {
    List<AdminClub> recentClub();
    List<ClubResponse> allClub();
    boolean deleteSingleClub(int id);
}
