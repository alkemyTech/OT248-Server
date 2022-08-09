package com.alkemy.ong.controller;

import com.alkemy.ong.exception.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping
public class ActivityController {

//    @Autowired
//    private ActivityService activityService;
//
//    @PostMapping("/activities")
//    public ResponseEntity<?> registerActivities(@Valid @RequestBody ActivityDto activityDto) {
//        try {
//            return ResponseEntity
//                    .status(HttpStatus.CREATED)
//                    .body(activityService.saveActivity(activityDto));
//        } catch (Exception exception) {
//            throw new ApiError(HttpStatus.BAD_REQUEST, exception);
//        }
//    }
}
