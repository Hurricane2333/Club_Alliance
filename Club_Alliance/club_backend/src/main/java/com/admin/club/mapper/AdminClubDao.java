package com.admin.club.mapper;

import com.admin.club.entity.AdminClub;
import com.admin.club.entity.AdminClubExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminClubDao {
    long countByExample(AdminClubExample example);

    int deleteByExample(AdminClubExample example);

    int deleteByPrimaryKey(Integer clubId);

    int insert(AdminClub record);

    int insertSelective(AdminClub record);

    List<AdminClub> selectByExample(AdminClubExample example);

    AdminClub selectByPrimaryKey(Integer clubId);

    int updateByExampleSelective(@Param("record") AdminClub record, @Param("example") AdminClubExample example);

    int updateByExample(@Param("record") AdminClub record, @Param("example") AdminClubExample example);

    int updateByPrimaryKeySelective(AdminClub record);

    int updateByPrimaryKey(AdminClub record);
}