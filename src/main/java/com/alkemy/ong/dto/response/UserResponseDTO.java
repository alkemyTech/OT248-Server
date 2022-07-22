package com.alkemy.ong.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class UserResponseDTO {

    private String name;
    private String lastName;
    private String email;
    private String photo;
    private Date createdOn;
    private Date updateOn;

}
