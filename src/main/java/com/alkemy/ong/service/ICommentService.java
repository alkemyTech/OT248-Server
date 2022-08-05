package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.model.Comment;
import javassist.NotFoundException;

public interface ICommentService {
    public CommentDto save(CommentDto dto);
    public void deleteById(Long id) throws NotFoundException;
}
