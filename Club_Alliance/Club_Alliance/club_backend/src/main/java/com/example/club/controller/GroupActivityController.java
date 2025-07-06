package com.example.club.controller;

import com.example.club.entity.GroupActivity;
import com.example.club.enums.ClubActivityStatus;
import com.example.club.service.GroupActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("group/activity")
public class GroupActivityController {

    @Autowired
    private GroupActivityService groupActivityService;

    @GetMapping("selectAll")
    private List<GroupActivity> queryAllGroupActivity() {
        return groupActivityService.queryAllGroupActivity();
    }

    @GetMapping("selectId/{activityId}")
    private GroupActivity queryGroupActivityById(@PathVariable("activityId") Integer activityId) {
        return groupActivityService.queryGroupActivityById(activityId);
    }

    @GetMapping("selectClubId/{clubId}")
    private List<GroupActivity> queryGroupActivityByClubId(@PathVariable("clubId") Integer clubId) {
        return groupActivityService.queryGroupActivityByClubId(clubId);
    }

    @GetMapping("selectUserId/{userId}")
    private List<GroupActivity> queryGroupActivityByUserId(@PathVariable("userId") Integer userId) {
        return groupActivityService.queryGroupActivityByUserId(userId);
    }

    @GetMapping("selectTitle/{title}")
    private List<GroupActivity> queryGroupActivityByTitle(@PathVariable("title") String title) {
        return groupActivityService.queryGroupActivityByTitle(title);
    }

    @GetMapping("selectStatus/{status}")
    private List<GroupActivity> queryGroupActivityByStatus(@PathVariable("status") ClubActivityStatus status) {
        return groupActivityService.queryGroupActivityByStatus(status);
    }

    @PostMapping("insert")
    private int insertGroupActivity(@RequestBody GroupActivity groupActivity) {
        return groupActivityService.insertGroupActivity(groupActivity);
    }

    @DeleteMapping("delete/{activityId}")
    private int deleteGroupActivityById(@PathVariable("activityId") Integer activityId) {
        return groupActivityService.deleteGroupActivityById(activityId);
    }

    @PutMapping("update")
    private int updateGroupActivityById(@RequestBody GroupActivity groupActivity) {
        return groupActivityService.updateGroupActivityById(groupActivity);
    }

}
