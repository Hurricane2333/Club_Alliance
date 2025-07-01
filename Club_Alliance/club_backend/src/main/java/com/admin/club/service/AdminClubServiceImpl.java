package com.admin.club.service;

import com.admin.club.entity.AdminClub;
import com.admin.club.entity.AdminClubExample;
import com.admin.club.entity.AdminUser;
import com.admin.club.entity.ClubResponse;
import com.admin.club.mapper.AdminClubDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class AdminClubServiceImpl implements AdminClubService {
    @Autowired
    private AdminClubDao adminClubDao;
    public AdminUserService adminUserService;

    public List<AdminClub> recentClub() {
        AdminClubExample example = new AdminClubExample();
        example.setOrderByClause("created_at asc");
        example.setLimit(8);
        return adminClubDao.selectByExample(example);
    }

    public List<ClubResponse> allClub() {
        AdminClubExample example = new AdminClubExample();
        example.setOrderByClause("club_id desc");
        AdminClubExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo("APPROVED");
        List<ClubResponse> clubResponse=new ArrayList<>();
        for(var club:adminClubDao.selectByExample(example)){
            clubResponse.add((ClubResponse) club);
            AdminUser adminUser=adminUserService.findUserByID(club.getPresidentId());
        }
        return clubResponse;
    }
}