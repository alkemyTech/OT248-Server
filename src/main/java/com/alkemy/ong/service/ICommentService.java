package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.dto.response.CommentResponseDTO;
import javassist.NotFoundException;
import java.util.List;

public interface ICommentService {

    CommentDto save(CommentDto dto);

    void deleteById(Long id) throws NotFoundException;

    List<CommentDto> findCommentByNewsId(Long newsId) throws Exception;

    CommentDto findById(Long id);

    List<CommentResponseDTO> getAllResponseDto();

}
