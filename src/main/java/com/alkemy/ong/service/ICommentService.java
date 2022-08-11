package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentDto;
import java.util.List;
import javassist.NotFoundException;

public interface ICommentService {
    CommentDto save(CommentDto dto);
    void deleteById(Long id) throws NotFoundException;
    List<CommentDto> findCommentByNewsId(Long newsId) throws Exception;
}
