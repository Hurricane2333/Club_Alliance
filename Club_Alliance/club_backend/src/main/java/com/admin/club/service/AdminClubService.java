package com.admin.club.service;

import com.admin.club.entity.AdminClub;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminClubService {
    List<AdminClub> recentClub();
}
