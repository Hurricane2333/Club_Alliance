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
    boolean createClub(AdminClub adminClub);
    boolean editClub(AdminClub adminClub);
    List<ClubResponse> searchClub(String name);
    int getPresidentId(int clubId);
    boolean modifyPresident(int clubId,int userId);
    List<ClubResponse> allClubToOperate();
    boolean passClub(int clubId);
    boolean refuseClub(int clubId);
    List<ClubResponse> searchClubToOperate(String name);
    int findClubByName(String name);
    AdminClub findClubById(int clubId);
}
