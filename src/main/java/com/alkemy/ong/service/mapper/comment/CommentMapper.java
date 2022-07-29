package com.alkemy.ong.service.mapper.comment;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.model.News;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
        commentDto.setId(comment.getId());
        commentDto.setBody(comment.getBody());
        commentDto.setUserId(comment.getUser().getId());
        commentDto.setNewsId(comment.getNews().getId());
        return commentDto;
    }
    public Comment dtoToComment(CommentDto dto) throws Exception{
        Users user = usersRepository.findById(dto.getId()).orElse(null);
        News news = newsRepository.findById(dto.getNewsId()).orElse(null);
        if (user == null) {
            throw new NotFoundException("user not found");
        }
        if (news == null) {
            throw new NotFoundException("news not found");
        }
        Comment comment = new Comment();
        comment.setBody(dto.getBody());
        comment.setUser(user);
        comment.setNews(news);
        return comment;
    }
}
