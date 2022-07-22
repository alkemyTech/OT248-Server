package com.alkemy.ong.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Jwt {

    private String token;

}
