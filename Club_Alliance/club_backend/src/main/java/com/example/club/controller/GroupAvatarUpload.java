package com.example.club.controller;

import com.example.club.service.GroupInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class GroupAvatarUpload {

    @Autowired
    private GroupInfoService groupInfoService;

}
