package com.example.club.controller;

import com.example.club.entity.ClubMember;
import com.example.club.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/club_member")
public class ApplyController {
    @Autowired
    private ApplyService applyService;

    @PostMapping("/apply")
    public String apply(@RequestBody ClubMember clubMember) {
        boolean success = applyService.applyClub(clubMember);
        if (success) {
            return "{\"code\":\"success\",\"msg\":\"申请已提交，等待审核\"}";
        } else {
            return "{\"code\":\"fail\",\"msg\":\"已申请或已加入该社团\"}";
        }
    }

    @GetMapping("/joined")
    public java.util.List<java.util.Map<String, Object>> getJoinedClubs(@RequestParam Integer userId) {
        return applyService.getJoinedClubs(userId);
    }
}