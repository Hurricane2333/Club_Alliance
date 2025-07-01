package com.admin.club.service;

import com.admin.club.entity.AdminUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface AdminUserService {
    ArrayList<Long> infoCount();
    AdminUser findUserByID(int id);
}
