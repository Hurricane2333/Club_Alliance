package com.example.club.mapper;

import com.example.club.entity.GroupMember;
import com.example.club.enums.ClubMemberStatus;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GroupMemberMapper {

    @Select("select * from club_member")
    List<GroupMember> queryAllGroupMember();

    @Select("select * from club_member where relation_id = #{relationId}")
    GroupMember queryGroupMemberById(Integer relationId);

    @Select("select * from club_member where user_id = #{userId}")
    List<GroupMember> queryGroupMemberByUserId(Integer userId);

    @Select("select * from club_member where club_id = #{clubId}")
    List<GroupMember> queryGroupMemberByClubId(Integer clubId);

    @Select("select * from club_member where status = #{status}")
    List<GroupMember> queryGroupMemberByStatus(ClubMemberStatus status);

    @Insert("insert into club_member (user_id, club_id, apply_reason) values(#{userId}, #{clubId}, #{applyReason})")
    int insertGroupMember(GroupMember groupMember);

    @Delete("delete from club_member where relation_id = #{relationId}")
    int deleteGroupMemberById(Integer relationId);

    @Update("update club_member set status = #{status}, reject_reason = #{rejectReason} where relation_id = #{relationId}")
    int updateGroupMemberById(GroupMember groupMember);

    @Select("select * from club_member where club_id = #{clubId} and status = #{status}")
    List<GroupMember> queryGroupMemberByClubIdAndStatus(Integer clubId, ClubMemberStatus status);

}
