package com.admin.club.service;

import com.admin.club.entity.AdminClubExample;
import com.admin.club.entity.AdminUserExample;
import com.admin.club.mapper.AdminClubDao;
import com.admin.club.mapper.AdminUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    private AdminUserDao adminUserDao;
    @Autowired
    private AdminClubDao adminClubDao;

    @Override
    public ArrayList<Long> infoCount() {
        AdminUserExample example0 = new AdminUserExample();
        ArrayList<Long> res=new ArrayList<>();
        res.add(adminUserDao.countByExample(example0));
        AdminClubExample example1 = new AdminClubExample();
        res.add(adminClubDao.countByExample(example1));
        return res;
    }
}
