package com.admin.club.service;

import com.admin.club.entity.AdminClub;
import com.admin.club.entity.AdminClubExample;
import com.admin.club.mapper.AdminClubDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminClubServiceImpl implements AdminClubService {
    @Autowired
    private AdminClubDao adminClubDao;

    public List<AdminClub> recentClub() {
        AdminClubExample example = new AdminClubExample();
        example.setOrderByClause("created_at asc");
        example.setLimit(8);
        return adminClubDao.selectByExample(example);
    }
}
