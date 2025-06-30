package com.admin.club.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface AdminUserService {
    ArrayList<Long> infoCount();
}
