package com.alkemy.ong.service.mapper;

import com.alkemy.ong.dto.ActivityRequestDTO;
import com.alkemy.ong.dto.response.ActivityResponseDTO;
import com.alkemy.ong.model.Activity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ActivityMapper {

    public Activity updateEntity (ActivityRequestDTO request, Activity entityFromDB){
        return Activity.builder()
                .id(entityFromDB.getId())
                .name(request.getName())
                .image(request.getImage())
                .content(request.getContent())
                .createAt(entityFromDB.getCreateAt())
                .updateAt(new Date())
                .deleted(entityFromDB.isDeleted())
                .build();
    }

    public ActivityResponseDTO entityToResponseDTO (Activity activity){

        return ActivityResponseDTO.builder()
                .id(activity.getId())
                .name(activity.getName())
                .image(activity.getImage())
                .content(activity.getContent())
                .updateDate(activity.getUpdateAt())
                .build();

    }

    public Activity requestDTOToEntity (ActivityRequestDTO activityRequestDTO){
        return Activity.builder()
                .name(activityRequestDTO.name)
                .content(activityRequestDTO.content)
                .image(activityRequestDTO.image)
                .createAt(new Date())
                .build();
    }
}
