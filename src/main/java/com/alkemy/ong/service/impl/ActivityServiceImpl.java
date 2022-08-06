package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ActivityRequestDTO;
import com.alkemy.ong.dto.response.ActivityResponseDTO;
import com.alkemy.ong.exception.NameAlreadyExists;
import com.alkemy.ong.model.Activity;
import com.alkemy.ong.repository.ActivityRepository;
import com.alkemy.ong.service.ActivityService;
import com.alkemy.ong.service.mapper.ActivityMapper;
import com.amazonaws.services.kms.model.AlreadyExistsException;
import com.amazonaws.services.workdocs.model.EntityAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
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

    @Transactional
    public ActivityResponseDTO save(ActivityRequestDTO activityRequestDTO) throws NameAlreadyExists {
        if (activityRepository.existsByName(activityRequestDTO.getName()))
            throw new NameAlreadyExists(messageSource.getMessage("error.activity.already.exists", null, Locale.US));
        Activity activity = activityMapper.requestDTOToEntity(activityRequestDTO);
        Activity activitySaved = activityRepository.save(activity);
        return activityMapper.entityToResponseDTO(activitySaved);
    }
}
