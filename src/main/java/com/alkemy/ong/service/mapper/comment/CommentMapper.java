package com.alkemy.ong.service.mapper.comment;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.dto.response.CommentResponseDTO;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.model.News;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.repository.UserRepository;
import com.amazonaws.services.cognitoidp.model.UserNotFoundException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class CommentMapper {

    @Autowired
    private UserRepository usersRepository;
    @Autowired
    private NewsRepository newsRepository;

    public CommentMapper() {
    }

    public CommentDto commentToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setBody(comment.getBody());
        commentDto.setNewsId(comment.getNews().getId());
        return commentDto;
    }

    public CommentResponseDTO entityToResponseDTO (Comment comment){
        return CommentResponseDTO.builder()
                .id(comment.getId())
                .body(comment.getBody())
                .newsId(comment.getNews().getId())
                .userId(comment.getUser().getId())
                .build();
    }

    public Comment dtoToComment(CommentDto dto, Long userId) throws Exception{
        Users user = usersRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        News news = newsRepository.findById(dto.getNewsId()).orElseThrow(() -> new EntityNotFoundException("New with that id not found."));

        return Comment.builder()
                .body(dto.getBody())
                .user(user)
                .news(news)
                .build();
    }

    public CommentResponseDTO commentToResponseDto(Comment comment) {
        return CommentResponseDTO
                .builder()
                .id(comment.getId())
                .body(comment.getBody())
                .userId(comment.getUser().getId())
                .newsId(comment.getNews().getId())
                .build();
    }
}
