package com.example.club.controller;

import com.example.club.entity.GroupPost;
import com.example.club.service.GroupPostService;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("group/post")
public class GroupPostController {

    @Autowired
    private GroupPostService groupPostService;

    @GetMapping("selectAll")
    private List<GroupPost> queryAllGroupPost() {
        return groupPostService.queryAllGroupPost();
    }

    @GetMapping("selectId/{postId}")
    private GroupPost queryGroupPostById(@PathVariable("postId") Integer postId) {
        return groupPostService.queryGroupPostById(postId);
    }

    @GetMapping("selectClubId/{clubId}")
    private List<GroupPost> queryGroupPostByClubId(@PathVariable("clubId") Integer clubId) {
        return groupPostService.queryGroupPostByClubId(clubId);
    }

    @GetMapping("selectUserId/{userId}")
    private List<GroupPost> queryGroupPostByUserId(@PathVariable("userId") Integer userId) {
        return groupPostService.queryGroupPostByUserId(userId);
    }

    @GetMapping("selectTitle/{title}")
    private List<GroupPost> queryGroupPostByTitle(@PathVariable("title") String title) {
        return groupPostService.queryGroupPostByTitle(title);
    }

    @PostMapping("insert")
    private int insertGroupPost(@RequestBody GroupPost groupPost) {
        return groupPostService.insertGroupPost(groupPost);
    }

    @DeleteMapping("delete/{postId}")
    private int deleteGroupPostById(@PathVariable("postId") Integer postId) {
        return groupPostService.deleteGroupPostById(postId);
    }

    @PutMapping("update")
    private int updateGroupPostById(@RequestBody GroupPost groupPost) {
        return groupPostService.updateGroupPostById(groupPost);
    }

}
