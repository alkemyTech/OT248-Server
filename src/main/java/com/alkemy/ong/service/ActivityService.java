package com.alkemy.ong.service;

import com.alkemy.ong.dto.ActivityRequestDTO;
import com.alkemy.ong.dto.response.ActivityResponseDTO;

public interface ActivityService {

    ActivityResponseDTO update (Long id, ActivityRequestDTO activityRequestDTO);

    ActivityResponseDTO save (ActivityRequestDTO activityRequestDTO);
}
