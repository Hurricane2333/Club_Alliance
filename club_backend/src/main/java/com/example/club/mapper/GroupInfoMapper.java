package com.example.club.mapper;

import com.example.club.entity.GroupInfo;
import com.example.club.enums.ClubCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GroupInfoMapper {

    @Select("select * from club")
    List<GroupInfo> queryAllGroupInfo();

    @Select("select * from club where club_name = #{clubName}")
    List<GroupInfo> queryGroupInfoByName(String clubName);

    @Select("select * from club where category = #{category}")
    List<GroupInfo> queryGroupInfoByCategory(ClubCategory category);

    @Select("select * from club where club_id = #{clubId}")
    GroupInfo queryGroupInfoById(Integer clubId);

    @Insert("insert into club (club_name, description, category, icon, president_id, requirements, current_members) values(#{clubName}, #{description}, #{category}, #{icon}, #{presidentId}, #{requirements}, #{currentMembers})")
    int insertGroupInfo(GroupInfo groupInfo);

    @Delete("delete from club where club_id = #{clubId}")
    int deleteGroupInfoById(Integer clubId);

    @Update("update club set club_name = #{clubName}, description = #{description}, category = #{category}, icon = #{icon}, president_id = #{presidentId}, requirements = #{requirements}, status = #{status} where club_id = #{clubId}")
    int updateGroupInfoById(GroupInfo groupInfo);

}
