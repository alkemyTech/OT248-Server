package com.alkemy.ong.util;

import com.alkemy.ong.dto.CategoryDto;
import lombok.Data;
import java.util.List;

@Data
public class CategoryResponse {

    private List<CategoryDto> content;
    private int numPage;
    private int sizePage;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;
}