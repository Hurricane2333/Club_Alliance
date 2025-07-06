package com.admin.club.mapper;

import com.admin.club.entity.AdminClubMember;
import com.admin.club.entity.AdminClubMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminClubMemberDao {
    long countByExample(AdminClubMemberExample example);

    int deleteByExample(AdminClubMemberExample example);

    int deleteByPrimaryKey(Integer relationId);

    int insert(AdminClubMember record);

    int insertSelective(AdminClubMember record);

    List<AdminClubMember> selectByExample(AdminClubMemberExample example);

    AdminClubMember selectByPrimaryKey(Integer relationId);

    int updateByExampleSelective(@Param("record") AdminClubMember record, @Param("example") AdminClubMemberExample example);

    int updateByExample(@Param("record") AdminClubMember record, @Param("example") AdminClubMemberExample example);

    int updateByPrimaryKeySelective(AdminClubMember record);

    int updateByPrimaryKey(AdminClubMember record);
}