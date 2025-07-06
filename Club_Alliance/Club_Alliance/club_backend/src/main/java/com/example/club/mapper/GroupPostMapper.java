package com.example.club.mapper;

import com.example.club.entity.GroupPost;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GroupPostMapper {

    @Select("select * from club_post")
    List<GroupPost> queryAllGroupPost();

    @Select("select * from club_post where post_id = #{postId}")
    GroupPost queryGroupPostById(Integer postId);

    @Select("select * from club_post where club_id = #{clubId}")
    List<GroupPost> queryGroupPostByClubId(Integer clubId);

    @Select("select * from club_post where user_id = #{userId}")
    List<GroupPost> queryGroupPostByUserId(Integer userId);

    @Select("select * from club_post where title = #{title}")
    List<GroupPost> queryGroupPostByTitle(String title);

    @Insert("insert into club_post (club_id, user_id, title, content) values(#{clubId}, #{userId}, #{title}, #{content})")
    int insertGroupPost(GroupPost groupPost);

    @Delete("delete from club_post where post_id = #{postId}")
    int deleteGroupPostById(Integer postId);

    @Update("update club_post set title = #{title}, content = #{content}, visibility = #{visibility}, status = #{status} where post_id = #{postId}")
    int updateGroupPostById(GroupPost groupPost);

}
