package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.model.News;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.ICommentService;
import com.alkemy.ong.service.mapper.comment.CommentMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.context.MessageSource;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentMapper commentMapper;
    
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
            if (comment.isPresent()){
                commentRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new NotFoundException("Comment not found");
        }
    }

    @Override
    public List<CommentDto> findCommentByNewsId(Long newsId) throws Exception{
        List<CommentDto> commentsDto = new ArrayList<>();
        Optional<News> response = newsRepository.findById(newsId);
        if (!response.isPresent()) {
            throw new EntityNotFoundException(messageSource.getMessage("news.notFound", null, Locale.US));
        }else{
            List<Comment> comments = commentRepository.findCommentByNewsId(newsId);
            if (comments.isEmpty()) {
                throw new Exception(messageSource.getMessage("news.comment.empty", null, Locale.US));
            }else{
                for (Comment aux : comments) {
                    CommentDto convertComment = commentMapper.commentToDto(aux);
                    commentsDto.add(convertComment);
                }
 
            }
        }
        return commentsDto;
    }
}
