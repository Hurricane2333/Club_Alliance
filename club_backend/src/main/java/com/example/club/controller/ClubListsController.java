package com.example.club.controller;

import com.example.club.entity.Club;
import com.example.club.enums.ClubStatus;
import com.example.club.mapper.ClubMapper;
import com.example.club.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clublists")
public class ClubListsController {

    @Autowired
    private ClubMapper clubMapper;

    /**
     * 获取所有已审核社团列表
     * @return Result<List<Club>>
     */
    @GetMapping
    public Result getAllClubs() {
        try {
            List<Club> clubs = clubMapper.selectAllApprovedClubs(ClubStatus.APPROVED);
            return Result.success(clubs);
        } catch (Exception e) {
            return Result.error("获取社团列表失败: " + e.getMessage());
        }
    }
}