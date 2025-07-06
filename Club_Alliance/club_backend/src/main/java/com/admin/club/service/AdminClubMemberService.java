package com.admin.club.service;

import com.admin.club.entity.AdminClubMember;
import com.admin.club.entity.AdminUser;
import com.admin.club.entity.MemberResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminClubMemberService {
    List<MemberResponse> allClubMember(int clubId);
    List<MemberResponse> searchMember(int clubId,String name);
    boolean createMember(AdminClubMember adminClubMember);
    boolean removeMember(int clubId,int userId);
    List<String> searchClub(int userId);
}
