package com.admin.club.mapper;

import com.admin.club.entity.AdminClubActivity;
import com.admin.club.entity.AdminClubActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminClubActivityDao {
    long countByExample(AdminClubActivityExample example);

    int deleteByExample(AdminClubActivityExample example);

    int deleteByPrimaryKey(Integer activityId);

    int insert(AdminClubActivity record);

    int insertSelective(AdminClubActivity record);

    List<AdminClubActivity> selectByExample(AdminClubActivityExample example);

    AdminClubActivity selectByPrimaryKey(Integer activityId);

    int updateByExampleSelective(@Param("record") AdminClubActivity record, @Param("example") AdminClubActivityExample example);

    int updateByExample(@Param("record") AdminClubActivity record, @Param("example") AdminClubActivityExample example);

    int updateByPrimaryKeySelective(AdminClubActivity record);

    int updateByPrimaryKey(AdminClubActivity record);
}