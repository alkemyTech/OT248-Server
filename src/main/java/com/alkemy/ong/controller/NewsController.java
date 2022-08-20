package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.dto.response.CommentResponseDTO;
import com.alkemy.ong.dto.response.NewsPageResponse;
import com.alkemy.ong.exception.ApiError;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.service.NewsService;
import com.alkemy.ong.service.impl.CommentServiceImpl;
import com.alkemy.ong.util.documentation.NewsDocumentation;
import com.amazonaws.services.kms.model.AlreadyExistsException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/news")
public class NewsController implements NewsDocumentation {

    @Autowired
    private NewsService newsService;

    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private CommentServiceImpl commentServiceImpl;


    @PostMapping
    public ResponseEntity<NewsDto> createNews(@Valid @RequestBody NewsDto newsDto){
        return new ResponseEntity<NewsDto>(newsService.createNews(newsDto), CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<NewsPageResponse> getNewsPaginated (@RequestParam(defaultValue = "1") Integer page) throws NotFoundException {
        return ResponseEntity.ok().body(newsService.pagination(page));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detailsNew(@Valid @PathVariable(value = "id") Long id) {
        try {
            NewsDto newsDto = newsService.findNewsById(id);
            return ResponseEntity
                    .ok()
                    .body(newsDto);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsDto> updateNews (@Valid @PathVariable(value = "id") Long id, @RequestBody NewsDto newsUpdate ) {
        try {
            NewsDto newsDto = newsService.findNewsById(id);
        } catch (Exception exception) {
            return ResponseEntity.status(NOT_FOUND).build();
        }
        NewsDto newsDtoResponse = newsService.updateNews(newsUpdate, id);
        return ResponseEntity.status(OK).body(newsDtoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable(name = "id") Long id) {
        try {
            newsService.deleteById(id);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), NOT_FOUND);
        }
        return new ResponseEntity<>(messageSource.getMessage("news.deleted.message", null, Locale.US), OK);
    }

    @GetMapping("/{newsId}/comments")
    public ResponseEntity<?> findCommentByNewsId(@PathVariable("newsId") Long newsId){
     List<CommentResponseDTO> commentsDto;
        try {
            commentsDto = commentServiceImpl.findCommentByNewsId(newsId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), NOT_FOUND);
        }
     return ResponseEntity.status(OK).body(commentsDto);
    }

}
