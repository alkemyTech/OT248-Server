package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ActivityRequestDTO;
import com.alkemy.ong.dto.response.ActivityResponseDTO;
import com.alkemy.ong.exception.ApiError;
import com.alkemy.ong.model.Activity;
import com.alkemy.ong.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @PostMapping
    public ResponseEntity<?> registerActivities(@Valid @RequestBody ActivityRequestDTO activityRequestDTO) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(activityService.save(activityRequestDTO));
        } catch (Exception exception) {
            throw new ApiError(HttpStatus.BAD_REQUEST, exception);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityResponseDTO> update (@PathVariable Long id, @Valid @RequestBody ActivityRequestDTO request){
        ActivityResponseDTO response = activityService.update(id, request);
        return ResponseEntity.ok().body(response);
    }

}
