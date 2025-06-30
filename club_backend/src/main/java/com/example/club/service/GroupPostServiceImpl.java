package com.example.club.service;

import com.example.club.entity.GroupPost;
import com.example.club.mapper.GroupPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupPostServiceImpl implements GroupPostService {

    @Autowired
    private GroupPostMapper groupPostMapper;

    @Override
    public List<GroupPost> queryAllGroupPost() {
        return groupPostMapper.queryAllGroupPost();
    }

    @Override
    public GroupPost queryGroupPostById(Integer postId) {
        return groupPostMapper.queryGroupPostById(postId);
    }

    @Override
    public List<GroupPost> queryGroupPostByClubId(Integer clubId) {
        return groupPostMapper.queryGroupPostByClubId(clubId);
    }

    @Override
    public List<GroupPost> queryGroupPostByUserId(Integer userId) {
        return groupPostMapper.queryGroupPostByUserId(userId);
    }

    @Override
    public List<GroupPost> queryGroupPostByTitle(String title) {
        return groupPostMapper.queryGroupPostByTitle(title);
    }

    @Override
    public int insertGroupPost(GroupPost groupPost) {
        return groupPostMapper.insertGroupPost(groupPost);
    }

    @Override
    public int deleteGroupPostById(Integer postId) {
        return groupPostMapper.deleteGroupPostById(postId);
    }

    @Override
    public int updateGroupPostById(GroupPost groupPost) {
        return groupPostMapper.updateGroupPostById(groupPost);
    }

}
