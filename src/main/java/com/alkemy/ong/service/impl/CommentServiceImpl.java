package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentDto;

import com.alkemy.ong.dto.response.UserResponseDTO;
import com.alkemy.ong.exception.ForbiddenUpdate;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.repository.UserRepository;

import com.alkemy.ong.dto.response.CommentResponseDTO;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.model.News;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.repository.NewsRepository;

import com.alkemy.ong.service.ICommentService;
import com.alkemy.ong.service.UserService;
import com.alkemy.ong.service.mapper.comment.CommentMapper;
import java.util.ArrayList;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.Locale;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements ICommentService {


    @Autowired
    private CommentRepository commentRepository;
    @Autowired private UserRepository userRepository;
    @Autowired
    private CommentMapper commentMapper;


    @Autowired private UserService userService;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private MessageSource messageSource;


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
            if (comment.isPresent()) {
                commentRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new NotFoundException(messageSource.getMessage("error.comment.notFound", null, Locale.US));
        }
    }

    @Override

    public CommentDto updateComment(CommentDto commentDto, Long id, String token) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment","id",id));

        UserResponseDTO userResponseDTO = userService.getUserDataByToken(token);

        if( !(comment.getUser().getEmail().equals(userResponseDTO.getEmail())
                || userRepository.findByEmail(userResponseDTO.getEmail()).getRole()
                .getName().equals("ROLE_ADMIN")  ) )
        {
            throw new ForbiddenUpdate("comment","id",id);
        }
        comment.setBody(commentDto.getBody());
        return commentMapper.commentToDto(commentRepository.save(comment));
    }

    public List<CommentDto> findCommentByNewsId(Long newsId) throws Exception {
        List<CommentDto> commentsDto = new ArrayList<>();
        Optional<News> response = newsRepository.findById(newsId);
        if (!response.isPresent()) {
            throw new EntityNotFoundException(messageSource.getMessage("news.notFound", null, Locale.US));
        } else {
            List<Comment> comments = commentRepository.findCommentByNewsId(newsId);
            if (comments.isEmpty()) {
                throw new Exception(messageSource.getMessage("news.comment.empty", null, Locale.US));
            } else {
                for (Comment aux : comments) {
                    CommentDto convertComment = commentMapper.commentToDto(aux);
                    commentsDto.add(convertComment);
                }

            }
        }
        return commentsDto;
    }

    @Override
    public CommentDto findById(Long id) {
        Optional<Comment> res = commentRepository.findById(id);
        if (res.isPresent()) {
            Comment comment = res.get();
            return commentMapper.commentToDto(comment);
        } else {
            throw new EntityNotFoundException(messageSource.getMessage("error.comment.notFound", null, Locale.US));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponseDTO> getAllResponseDto() {
        return commentRepository.findAllByOrderByCreateAtAsc()
                .stream()
                .map(comment -> commentMapper.commentToResponseDto(comment))
                .collect(Collectors.toList());


    }
}
