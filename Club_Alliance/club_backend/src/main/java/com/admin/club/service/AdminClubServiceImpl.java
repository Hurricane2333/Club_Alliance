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

    public boolean deleteSingleClub(int clubId) {
        if(adminClubDao.deleteByPrimaryKey(clubId)!=0)
            return true;
        return false;
    }

    public boolean createClub(AdminClub adminClub) {
        if(adminClubDao.insert(adminClub)!=0)
            return true;
        return false;
    }

    public boolean editClub(AdminClub adminClub){
        if(adminClubDao.updateByPrimaryKey(adminClub)!=0)
            return true;
        return false;
    }

    public List<ClubResponse> searchClub(String name) {
        AdminClubExample example = new AdminClubExample();
        example.setOrderByClause("club_id desc");
        AdminClubExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo("APPROVED");
        criteria.andClubNameLike("%"+name+"%");
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

    public int getPresidentId(int clubId) {
        return adminClubDao.selectByPrimaryKey(clubId).getPresidentId();
    }

    public boolean modifyPresident(int clubId,int userId) {
        AdminClub adminClub=new AdminClub();
        adminClub.setClubId(clubId);
        adminClub.setPresidentId(userId);
        if(adminClubDao.updateByPrimaryKeySelective(adminClub)!=0)
            return true;
        return false;
    }

    public List<ClubResponse> allClubToOperate() {
        AdminClubExample example = new AdminClubExample();
        example.setOrderByClause("club_id desc");
        AdminClubExample.Criteria criteria0 = example.createCriteria();
        AdminClubExample.Criteria criteria1 = example.createCriteria();
        criteria0.andStatusEqualTo("PENDING");
        criteria1.andStatusEqualTo("REJECTED");
        example.or(criteria1);
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

    public boolean passClub(int clubId){
        AdminClub adminClub=new AdminClub();
        adminClub.setClubId(clubId);
        adminClub.setStatus("APPROVED");
        if(adminClubDao.updateByPrimaryKeySelective(adminClub)!=0)
            return true;
        return false;
    }

    public boolean refuseClub(int clubId){
        AdminClub adminClub=new AdminClub();
        adminClub.setClubId(clubId);
        adminClub.setStatus("REJECTED");
        if(adminClubDao.updateByPrimaryKeySelective(adminClub)!=0)
            return true;
        return false;
    }

    public List<ClubResponse> searchClubToOperate(String name) {
        AdminClubExample example = new AdminClubExample();
        example.setOrderByClause("club_id desc");
        AdminClubExample.Criteria criteria0 = example.createCriteria();
        AdminClubExample.Criteria criteria1 = example.createCriteria();
        criteria0.andClubNameLike("%" + name + "%").andStatusEqualTo("PENDING");
        criteria1.andClubNameLike("%" + name + "%").andStatusEqualTo("REJECTED");
        example.or(criteria1);
        List<ClubResponse> clubResponse = new ArrayList<>();
        for (var club : adminClubDao.selectByExample(example)) {
            ClubResponse response = new ClubResponse();
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
            response.president = adminUserService.findUserByID(club.getPresidentId()).getStuName();
            clubResponse.add(response);
        }
        return clubResponse;
    }

    public int findClubByName(String name){
        AdminClubExample example = new AdminClubExample();
        AdminClubExample.Criteria criteria = example.createCriteria();
        criteria.andClubNameEqualTo(name);
        List<AdminClub> adminClubs = adminClubDao.selectByExample(example);
        if(adminClubs.size()>0)
            return adminClubs.get(0).getClubId();
        return -1;
    }

    public AdminClub findClubById(int clubId){
        AdminClubExample example = new AdminClubExample();
        AdminClubExample.Criteria criteria = example.createCriteria();
        criteria.andClubIdEqualTo(clubId);
        List<AdminClub> adminClubs = adminClubDao.selectByExample(example);
        if(adminClubs.size()>0)
            return adminClubs.get(0);
        return null;
    }
}