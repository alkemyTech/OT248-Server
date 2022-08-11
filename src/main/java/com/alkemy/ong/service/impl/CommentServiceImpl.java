package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.dto.response.UserResponseDTO;
import com.alkemy.ong.exception.ForbiddenUpdate;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.ICommentService;
import com.alkemy.ong.service.UserService;
import com.alkemy.ong.service.mapper.comment.CommentMapper;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CommentServiceImpl implements ICommentService {


    @Autowired
    private CommentRepository commentRepository;
    @Autowired private UserRepository userRepository;
    @Autowired
    private CommentMapper commentMapper;

    @Autowired private UserService userService;

    @Override
    @Transactional
    public CommentDto save(CommentDto dto) {
        try {
            Comment comment = commentMapper.dtoToComment(dto);
            Comment commentSaved = commentRepository.save(comment);
            return commentMapper.commentToDto(commentSaved);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws NotFoundException {
        try {
            Optional<Comment> comment = commentRepository.findById(id);
            if (comment.isPresent()){
                commentRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new NotFoundException("Comment not found");
        }
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Long id, String token) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment","id",id));

        UserResponseDTO userResponseDTO = userService.getUserDataByToken(token);

        if( !(comment.getUser().getEmail().equals(userResponseDTO.getEmail())
                || userRepository.findByEmail(userResponseDTO.getEmail()).getRole()
                .getName().equals("ADMIN")  ) )
        {
            throw new ForbiddenUpdate("comentario","id",id);
        }
        comment.setBody(commentDto.getBody());
        return commentMapper.commentToDto(commentRepository.save(comment));
    }
}
