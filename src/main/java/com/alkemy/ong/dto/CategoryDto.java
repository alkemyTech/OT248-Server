package com.alkemy.ong.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {

    private Long categoryId;

    @NotNull(message = "Name may not be null")
//    @Pattern(regexp = "[a-bA-B0-9]", message = "This field only accepts letters and numbers.")
    private String name;

    private String description;

    private String image;
    
    private Date createDate;
    
    private Date updateDate;
    
    
}
