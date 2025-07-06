package com.example.club.controller;

import com.example.club.entity.GroupActivityParticipant;
import com.example.club.enums.ActivityParticipant;
import com.example.club.service.GroupActivityParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("group/activity/participant")
public class GroupActivityParticipantController {

    @Autowired
    private GroupActivityParticipantService groupActivityParticipantService;

    @GetMapping("selectAll")
    private List<GroupActivityParticipant> queryAllGroupActivityParticipant() {
        return groupActivityParticipantService.queryAllGroupActivityParticipant();
    }

    @GetMapping("selectId/{participationId}")
    private GroupActivityParticipant queryGroupActivityParticipantById(@PathVariable("participationId") Integer participationId) {
        return groupActivityParticipantService.queryGroupActivityParticipantById(participationId);
    }

    @GetMapping("selectActivityId/{activityId}")
    private List<GroupActivityParticipant> queryGroupActivityParticipantByActivityId(@PathVariable("activityId") Integer activityId) {
        return groupActivityParticipantService.queryGroupActivityParticipantByActivityId(activityId);
    }

    @GetMapping("selectUserId/{userId}")
    private List<GroupActivityParticipant> queryGroupActivityParticipantByUserId(@PathVariable("userId") Integer userId) {
        return groupActivityParticipantService.queryGroupActivityParticipantByUserId(userId);
    }

    @GetMapping("selectStatus/{status}")
    private List<GroupActivityParticipant> queryGroupActivityParticipantByStatus(@PathVariable("status") ActivityParticipant status) {
        return groupActivityParticipantService.queryGroupActivityParticipantByStatus(status);
    }

    @PostMapping("insert")
    private int insertGroupActivityParticipant(@RequestBody GroupActivityParticipant groupActivityParticipant) {
        return groupActivityParticipantService.insertGroupActivityParticipant(groupActivityParticipant);
    }

    @DeleteMapping("delete/{participationId}")
    private int deleteGroupActivityParticipantById(@PathVariable("participationId") Integer participationId) {
        return groupActivityParticipantService.deleteGroupActivityParticipantById(participationId);
    }

    @PutMapping("update")
    private int updateGroupActivityParticipantById(@RequestBody GroupActivityParticipant groupActivityParticipant) {
        return groupActivityParticipantService.updateGroupActivityParticipantById(groupActivityParticipant);
    }

}
