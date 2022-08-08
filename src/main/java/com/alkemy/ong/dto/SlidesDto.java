package com.alkemy.ong.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlidesDto {

    private String imageUrl;

    
    private Integer order;

    

}
