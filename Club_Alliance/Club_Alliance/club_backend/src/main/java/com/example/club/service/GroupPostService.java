package com.example.club.service;

import com.example.club.entity.GroupPost;

import java.util.List;

public interface GroupPostService {

    List<GroupPost> queryAllGroupPost();
    GroupPost queryGroupPostById(Integer postId);
    List<GroupPost> queryGroupPostByClubId(Integer clubId);
    List<GroupPost> queryGroupPostByUserId(Integer userId);
    List<GroupPost> queryGroupPostByTitle(String title);
    int insertGroupPost(GroupPost groupPost);
    int deleteGroupPostById(Integer postId);
    int updateGroupPostById(GroupPost groupPost);

}
