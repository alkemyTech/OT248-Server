package com.alkemy.ong.controller;

import com.alkemy.ong.exception.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/activities")
    public ResponseEntity<?> registerActivities(@Valid @RequestBody ActivityDto activityDto) {
        if (activityService.isName(activityDto.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("The name is already in use"));
        }
        if (activityService.isContent(activityDto.getContent())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("The Content is already exist"));
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(activityService.saveActivity(activityDto));
    }
}
