package com.example.club.service;

import com.example.club.entity.AdminUser;
import com.example.club.entity.UserResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface AdminUserService {
    ArrayList<Long> infoCount();
    AdminUser findUserByID(int id);
    List<UserResponse> findAllAdmin();
    boolean createUser(AdminUser adminUser);
    boolean deleteSingleUser(int userId);
    List<UserResponse> searchAdmin(String stuName);
    boolean editAdmin(AdminUser adminUser);
    List<UserResponse> findAllStudent();
    List<UserResponse> searchStudent(String stuName);
    boolean editStudent(AdminUser adminUser);
}
