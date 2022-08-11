package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentDto;
import javassist.NotFoundException;

public interface ICommentService {
    CommentDto save(CommentDto dto);
    void deleteById(Long id) throws NotFoundException;

    CommentDto updateComment(CommentDto commentDto, Long id, String token);


}
