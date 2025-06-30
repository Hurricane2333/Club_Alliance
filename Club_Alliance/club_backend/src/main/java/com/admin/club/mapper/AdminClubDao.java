package com.admin.club.mapper;

import com.admin.club.entity.AdminClub;
import com.admin.club.entity.AdminClubExample;
import com.admin.club.entity.AdminClubWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminClubDao {
    long countByExample(AdminClubExample example);

    int deleteByExample(AdminClubExample example);

    int deleteByPrimaryKey(Integer clubId);

    int insert(AdminClubWithBLOBs record);

    int insertSelective(AdminClubWithBLOBs record);

    List<AdminClubWithBLOBs> selectByExampleWithBLOBs(AdminClubExample example);

    List<AdminClub> selectByExample(AdminClubExample example);

    AdminClubWithBLOBs selectByPrimaryKey(Integer clubId);

    int updateByExampleSelective(@Param("record") AdminClubWithBLOBs record, @Param("example") AdminClubExample example);

    int updateByExampleWithBLOBs(@Param("record") AdminClubWithBLOBs record, @Param("example") AdminClubExample example);

    int updateByExample(@Param("record") AdminClub record, @Param("example") AdminClubExample example);

    int updateByPrimaryKeySelective(AdminClubWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AdminClubWithBLOBs record);

    int updateByPrimaryKey(AdminClub record);
}