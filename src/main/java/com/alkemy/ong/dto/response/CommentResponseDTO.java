package com.alkemy.ong.dto.response;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
@Builder
public class CommentResponseDTO {
    private Long id;
    private String body;
    private Long userId;
    private Long newsId;

}
