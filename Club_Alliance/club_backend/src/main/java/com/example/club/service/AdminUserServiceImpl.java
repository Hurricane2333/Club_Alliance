package com.example.club.service;

import com.example.club.entity.AdminClubExample;
import com.example.club.entity.AdminUser;
import com.example.club.entity.AdminUserExample;
import com.example.club.entity.UserResponse;
import com.example.club.mapper.AdminClubDao;
import com.example.club.mapper.AdminUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    private AdminUserDao adminUserDao;
    @Autowired
    private AdminClubDao adminClubDao;
    @Lazy
    @Autowired
    private AdminClubMemberService adminClubMemberService;

    @Override
    public ArrayList<Long> infoCount() {
        AdminUserExample example0 = new AdminUserExample();
        ArrayList<Long> res=new ArrayList<>();
        res.add(adminUserDao.countByExample(example0));
        AdminClubExample example1 = new AdminClubExample();
        res.add(adminClubDao.countByExample(example1));
        return res;
    }

    public AdminUser findUserByID(int id){
        AdminUserExample example = new AdminUserExample();
        AdminUserExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(id);
        List<AdminUser> list = adminUserDao.selectByExample(example);
        if(list.size()>0)
            return list.get(0);
        return null;
    }

    public List<UserResponse> findAllAdmin() {
        AdminUserExample example = new AdminUserExample();
        AdminUserExample.Criteria criteria = example.createCriteria();
        criteria.andIsAdminEqualTo(true);
        List<AdminUser> adminUsers=adminUserDao.selectByExample(example);
        List<UserResponse> userResponses=new ArrayList<>();
        for(AdminUser adminUser:adminUsers){
            UserResponse userResponse=new UserResponse();
            userResponse.setUserId(adminUser.getUserId());
            userResponse.setStuName(adminUser.getStuName());
            userResponse.setEmail(adminUser.getEmail());
            userResponse.setAvatar(adminUser.getAvatar());
            userResponse.setCreatedAt(adminUser.getCreatedAt());
            userResponse.setStuId(adminUser.getStuId());
            userResponse.setPassword(adminUser.getPassword());
            userResponse.setIsAdmin(adminUser.getIsAdmin());
            List<String> clubNames=adminClubMemberService.searchClub(adminUser.getUserId());
            if(clubNames.size()>0)
                userResponse.joinedClub=String.join("，",clubNames);
            else userResponse.joinedClub="无";
            userResponses.add(userResponse);
        }
        return userResponses;
    }

    public boolean createUser(AdminUser adminUser) {
        if(adminUserDao.insert(adminUser)!=0)
            return true;
        return false;
    }

    public boolean deleteSingleUser(int userId) {
        if(adminUserDao.deleteByPrimaryKey(userId)!=0)
            return true;
        return false;
    }

    public List<UserResponse> searchAdmin(String stuName) {
        AdminUserExample example = new AdminUserExample();
        AdminUserExample.Criteria criteria = example.createCriteria();
        criteria.andIsAdminEqualTo(true);
        criteria.andStuNameLike("%"+stuName+"%");
        List<AdminUser> adminUsers=adminUserDao.selectByExample(example);
        List<UserResponse> userResponses=new ArrayList<>();
        for(AdminUser adminUser:adminUsers){
            UserResponse userResponse=new UserResponse();
            userResponse.setUserId(adminUser.getUserId());
            userResponse.setStuName(adminUser.getStuName());
            userResponse.setEmail(adminUser.getEmail());
            userResponse.setAvatar(adminUser.getAvatar());
            userResponse.setCreatedAt(adminUser.getCreatedAt());
            userResponse.setStuId(adminUser.getStuId());
            userResponse.setPassword(adminUser.getPassword());
            userResponse.setIsAdmin(adminUser.getIsAdmin());
            List<String> clubNames=adminClubMemberService.searchClub(adminUser.getUserId());
            if(clubNames.size()>0)
                userResponse.joinedClub=String.join("，",clubNames);
            else userResponse.joinedClub="无";
            userResponses.add(userResponse);
        }
        return userResponses;
    }

    public boolean editAdmin(AdminUser adminUser) {
        if(adminUserDao.updateByPrimaryKey(adminUser)!=0)
            return true;
        return false;
    }

    @Override
    public List<UserResponse> findAllStudent() {
        AdminUserExample example = new AdminUserExample();
        AdminUserExample.Criteria criteria = example.createCriteria();
        criteria.andIsAdminEqualTo(false);
        List<AdminUser> adminUsers=adminUserDao.selectByExample(example);
        List<UserResponse> userResponses=new ArrayList<>();
        for(AdminUser adminUser:adminUsers){
            UserResponse userResponse=new UserResponse();
            userResponse.setUserId(adminUser.getUserId());
            userResponse.setStuName(adminUser.getStuName());
            userResponse.setEmail(adminUser.getEmail());
            userResponse.setAvatar(adminUser.getAvatar());
            userResponse.setCreatedAt(adminUser.getCreatedAt());
            userResponse.setStuId(adminUser.getStuId());
            userResponse.setPassword(adminUser.getPassword());
            userResponse.setIsAdmin(adminUser.getIsAdmin());
            List<String> clubNames=adminClubMemberService.searchClub(adminUser.getUserId());
            if(clubNames.size()>0)
                userResponse.joinedClub=String.join("，",clubNames);
            else userResponse.joinedClub="无";
            userResponses.add(userResponse);
        }
        return userResponses;
    }

    public List<UserResponse> searchStudent(String stuName) {
        AdminUserExample example = new AdminUserExample();
        AdminUserExample.Criteria criteria = example.createCriteria();
        criteria.andIsAdminEqualTo(false);
        criteria.andStuNameLike("%"+stuName+"%");
        List<AdminUser> adminUsers=adminUserDao.selectByExample(example);
        List<UserResponse> userResponses=new ArrayList<>();
        for(AdminUser adminUser:adminUsers){
            UserResponse userResponse=new UserResponse();
            userResponse.setUserId(adminUser.getUserId());
            userResponse.setStuName(adminUser.getStuName());
            userResponse.setEmail(adminUser.getEmail());
            userResponse.setAvatar(adminUser.getAvatar());
            userResponse.setCreatedAt(adminUser.getCreatedAt());
            userResponse.setStuId(adminUser.getStuId());
            userResponse.setPassword(adminUser.getPassword());
            userResponse.setIsAdmin(adminUser.getIsAdmin());
            List<String> clubNames=adminClubMemberService.searchClub(adminUser.getUserId());
            if(clubNames.size()>0)
                userResponse.joinedClub=String.join("，",clubNames);
            else userResponse.joinedClub="无";
            userResponses.add(userResponse);
        }
        return userResponses;
    }

    public boolean editStudent(AdminUser adminUser) {
        if(adminUserDao.updateByPrimaryKey(adminUser)!=0)
            return true;
        return false;
    }
}
