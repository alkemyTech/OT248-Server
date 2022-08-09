package com.alkemy.ong.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentResponseDTO {
    private String body;
}
