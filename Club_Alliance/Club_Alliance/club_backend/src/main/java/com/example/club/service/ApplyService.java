package com.example.club.service;

import com.example.club.entity.ClubMember;
import com.example.club.enums.ClubMemberStatus;
import com.example.club.mapper.ApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {
    @Autowired
    private ApplyMapper applyMapper;

    public boolean applyClub(ClubMember clubMember) {
        // 检查是否已申请
        ClubMember exist = applyMapper.selectByUserIdAndClubId(clubMember.getUserId(), clubMember.getClubId());
        if (exist != null && exist.getStatus() != ClubMemberStatus.REJECTED) {
            return false; // 已申请或已加入
        }
        clubMember.setStatus(ClubMemberStatus.PENDING);
        return applyMapper.insertApplication(clubMember) > 0;
    }

    public java.util.List<java.util.Map<String, Object>> getJoinedClubs(Integer userId) {
        return applyMapper.selectJoinedClubsByUserId(userId);
    }
}