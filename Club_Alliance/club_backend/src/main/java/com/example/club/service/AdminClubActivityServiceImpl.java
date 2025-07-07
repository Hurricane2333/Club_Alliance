package com.example.club.service;

import com.example.club.entity.ActivityResponse;
import com.example.club.entity.AdminClubActivity;
import com.example.club.entity.AdminClubActivityExample;
import com.example.club.mapper.AdminClubActivityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminClubActivityServiceImpl implements AdminClubActivityService {
    @Autowired
    AdminClubActivityDao adminClubActivityDao;
    @Autowired
    AdminClubService adminClubService;
    @Autowired
    AdminUserService adminUserService;

    public List<ActivityResponse> allClubActivity() {
        AdminClubActivityExample example = new AdminClubActivityExample();
        example.setOrderByClause("activity_id desc");
        List<ActivityResponse> activityResponse=new ArrayList<>();
        for(var clubActivity:adminClubActivityDao.selectByExample(example)){
            ActivityResponse response=new ActivityResponse();
            response.setActivityId(clubActivity.getActivityId());
            response.setClubId(clubActivity.getClubId());
            response.setUserId(clubActivity.getUserId());
            response.setTitle(clubActivity.getTitle());
            response.setContent(clubActivity.getContent());
            response.setStartTime(clubActivity.getStartTime());
            response.setEndTime(clubActivity.getEndTime());
            response.setLocation(clubActivity.getLocation());
            response.setStatus(clubActivity.getStatus());
            response.setCurrentParticipants(clubActivity.getCurrentParticipants());
            response.setMaxParticipants(clubActivity.getMaxParticipants());
            response.setCreatedAt(clubActivity.getCreatedAt());
            response.clubName=adminClubService.findClubById(clubActivity.getClubId()).getClubName();
            response.userName=adminUserService.findUserByID(clubActivity.getUserId()).getStuName();
            activityResponse.add(response);
        }
        return activityResponse;
    }

    public boolean passActivity(int activityId){
        AdminClubActivity adminClubActivity=new AdminClubActivity();
        adminClubActivity.setActivityId(activityId);
        adminClubActivity.setStatus("ACTIVE");
        if(adminClubActivityDao.updateByPrimaryKeySelective(adminClubActivity)!=0)
            return true;
        return false;
    }

    public boolean refuseActivity(int activityId){
        AdminClubActivity adminClubActivity=new AdminClubActivity();
        adminClubActivity.setActivityId(activityId);
        adminClubActivity.setStatus("CANCELLED");
        if(adminClubActivityDao.updateByPrimaryKeySelective(adminClubActivity)!=0)
            return true;
        return false;
    }

    public boolean deleteActivity(int activityId) {
        if(adminClubActivityDao.deleteByPrimaryKey(activityId)!=0)
            return true;
        return false;
    }

    public List<ActivityResponse> searchActivity(String name) {
        AdminClubActivityExample example = new AdminClubActivityExample();
        example.setOrderByClause("activity_id desc");
        AdminClubActivityExample.Criteria criteria = example.createCriteria();
        criteria.andTitleLike("%"+name+"%");
        List<ActivityResponse> activityResponse=new ArrayList<>();
        for(var clubActivity:adminClubActivityDao.selectByExample(example)){
            ActivityResponse response=new ActivityResponse();
            response.setActivityId(clubActivity.getActivityId());
            response.setClubId(clubActivity.getClubId());
            response.setUserId(clubActivity.getUserId());
            response.setTitle(clubActivity.getTitle());
            response.setContent(clubActivity.getContent());
            response.setStartTime(clubActivity.getStartTime());
            response.setEndTime(clubActivity.getEndTime());
            response.setLocation(clubActivity.getLocation());
            response.setStatus(clubActivity.getStatus());
            response.setCurrentParticipants(clubActivity.getCurrentParticipants());
            response.setMaxParticipants(clubActivity.getMaxParticipants());
            response.setCreatedAt(clubActivity.getCreatedAt());
            response.clubName=adminClubService.findClubById(clubActivity.getClubId()).getClubName();
            response.userName=adminUserService.findUserByID(clubActivity.getUserId()).getStuName();
            activityResponse.add(response);
        }
        return activityResponse;
    }
}
