package com.admin.club.service;

import com.admin.club.entity.*;
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
    @Autowired
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
            ClubResponse response=new ClubResponse();
            response.setClubId(club.getClubId());
            response.setClubName(club.getClubName());
            response.setCategory(club.getCategory());
            response.setIcon(club.getIcon());
            response.setCreatedAt(club.getCreatedAt());
            response.setCurrentMembers(club.getCurrentMembers());
            response.setFavoriteCount(club.getFavoriteCount());
            response.setPresidentId(club.getPresidentId());
            response.setStatus(club.getStatus());
            response.setDescription(club.getDescription());
            response.setRequirements(club.getRequirements());
            response.president=adminUserService.findUserByID(club.getPresidentId()).getStuName();
            clubResponse.add(response);
        }
        return clubResponse;
    }

    public boolean deleteSingleClub(int id) {
        if(adminClubDao.deleteByPrimaryKey(id)!=0)
            return true;
        return false;
    }
}