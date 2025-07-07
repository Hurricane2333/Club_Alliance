package com.example.club.service;

import com.example.club.entity.*;
import com.example.club.mapper.AdminClubDao;
import com.example.club.mapper.AdminClubMemberDao;
import com.example.club.mapper.AdminUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminClubMemberServiceImpl implements AdminClubMemberService {
    @Autowired
    private AdminClubMemberDao adminClubMemberDao;
    @Autowired
    public AdminUserService adminUserService;
    @Autowired
    public AdminClubService adminClubService;
    @Autowired
    public AdminUserDao adminUserDao;
    @Autowired
    private AdminClubDao adminClubDao;

    public List<MemberResponse> allClubMember(int clubId){
        AdminClubMemberExample example = new AdminClubMemberExample();
        AdminClubMemberExample.Criteria criteria = example.createCriteria();
        criteria.andClubIdEqualTo(clubId);
        criteria.andStatusEqualTo("APPROVED");
        List<AdminClubMember> adminClubMember=adminClubMemberDao.selectByExample(example);
        List<MemberResponse> memberResponses=new ArrayList<>();
        for(AdminClubMember member:adminClubMember){
            MemberResponse memberResponse=new MemberResponse();
            AdminUser adminUser=adminUserService.findUserByID(member.getUserId());
            memberResponse.setUserId(adminUser.getUserId());
            memberResponse.setStuId(adminUser.getStuId());
            memberResponse.setStuName(adminUser.getStuName());
            memberResponse.reviewTime=member.getReviewTime();
            if(member.getUserId()==adminClubService.getPresidentId(clubId))
                memberResponse.isPresident="是";
            else memberResponse.isPresident="否";
            memberResponses.add(memberResponse);
        }
        return memberResponses;
    }

    public List<MemberResponse> searchMember(int clubId,String name){
        AdminClubMemberExample example = new AdminClubMemberExample();
        AdminClubMemberExample.Criteria criteria0 = example.createCriteria();
        criteria0.andClubIdEqualTo(clubId);
        criteria0.andStatusEqualTo("APPROVED");
        List<AdminClubMember> adminClubMember=adminClubMemberDao.selectByExample(example);
        List<MemberResponse> memberResponses=new ArrayList<>();
        for(AdminClubMember member:adminClubMember){
            AdminUser adminUser=adminUserService.findUserByID(member.getUserId());
            if(adminUser.getStuName().contains(name)){
                MemberResponse memberResponse=new MemberResponse();
                memberResponse.setUserId(adminUser.getUserId());
                memberResponse.setStuId(adminUser.getStuId());
                memberResponse.setStuName(adminUser.getStuName());
                memberResponse.reviewTime=member.getReviewTime();
                if(member.getUserId()==adminClubService.getPresidentId(clubId))
                    memberResponse.isPresident="是";
                else memberResponse.isPresident="否";
                memberResponses.add(memberResponse);
            }
        }
        return memberResponses;
    }

    public boolean createMember(AdminClubMember adminClubMember){
        if(adminClubMemberDao.insert(adminClubMember)!=0) {
            AdminClub adminClub=adminClubDao.selectByPrimaryKey(adminClubMember.getClubId());
            adminClub.setCurrentMembers(adminClub.getCurrentMembers());
            adminClubDao.updateByPrimaryKey(adminClub);
            return true;
        }
        return false;
    }

    public boolean removeMember(int clubId,int userId){
        AdminClubMemberExample example = new AdminClubMemberExample();
        AdminClubMemberExample.Criteria criteria = example.createCriteria();
        criteria.andClubIdEqualTo(clubId);
        criteria.andUserIdEqualTo(userId);
        criteria.andStatusEqualTo("APPROVED");
        if(adminClubMemberDao.deleteByExample(example)!=0){
            return true;
        }
        return false;
    }

    public List<String> searchClub(int userId) {
        AdminClubMemberExample example = new AdminClubMemberExample();
        AdminClubMemberExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andStatusEqualTo("APPROVED");
        List<AdminClubMember> adminClubMembers=adminClubMemberDao.selectByExample(example);
        List<String> clubNames=new ArrayList<>();
        for(AdminClubMember adminClubMember:adminClubMembers){
            clubNames.add(adminClubService.findClubById(adminClubMember.getClubId()).getClubName());
        }
        return clubNames;
    }
}
