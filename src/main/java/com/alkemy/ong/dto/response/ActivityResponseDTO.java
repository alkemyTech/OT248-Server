package com.alkemy.ong.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ActivityResponseDTO {

    public Long id;
    public String name;
    public String image;
    public String content;
    public Date updateDate;

}
