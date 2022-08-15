package com.alkemy.ong.dto.response;

import com.alkemy.ong.dto.TestimonialDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestimonialPageResponse {

    private List<TestimonialDto> content;

    private int pageNo;

    private int pageSize;

    private Long totalElements;

    private int totalPages;

    private boolean last;
}
