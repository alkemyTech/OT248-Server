package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ActivityRequestDTO;
import com.alkemy.ong.dto.response.ActivityResponseDTO;
import com.alkemy.ong.model.Activity;
import com.alkemy.ong.repository.ActivityRepository;
import com.alkemy.ong.service.ActivityService;
import com.alkemy.ong.service.mapper.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Locale;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public ActivityResponseDTO update(Long id, ActivityRequestDTO activityRequestDTO) {
        Activity activity = activityRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "Activity " + messageSource.getMessage(
                                        "not.found", null, Locale.US)));

        activity = activityMapper.updateEntity(activityRequestDTO, activity);
        Activity activityUpdated = activityRepository.save(activity);

        return activityMapper.entityToResponseDTO(activityUpdated);
    }

    @Override
    public ActivityResponseDTO save(ActivityRequestDTO activityRequestDTO) {
        return null;
    }
}
