package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.dto.response.NewsPageResponse;
import com.alkemy.ong.exception.ApiError;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.service.NewsService;
import com.alkemy.ong.service.impl.CommentServiceImpl;
import com.amazonaws.services.kms.model.AlreadyExistsException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
public class NewsController {

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

    @ApiOperation(value = "Get a page with 10 items according to the page number.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Page found successfully"),
            @ApiResponse(code = 404, message = "Page doesn't have elements" )})
    @ApiParam(value = "Number of page", required = true, defaultValue = "1")
    @GetMapping("/get-all")
    public ResponseEntity<NewsPageResponse> getNewsPaginated (@RequestParam(defaultValue = "1") Integer page) throws NotFoundException {
        return ResponseEntity.ok().body(newsService.pagination(page));
    }

    @ApiOperation(value = "Get a New by id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "New found successfully."),
            @ApiResponse(code = 404, message = "New with that id was not found")})
    @ApiParam(value = "New id", required = true)
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

    @ApiOperation(value = "Update a new by id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "New updated successfully"),
            @ApiResponse(code = 404, message = "New with that id doesn't exist")})
    @ApiParam(value = "New id", required = true)
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

    @ApiOperation(value = "Soft delete a new.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "New deleted successfully"),
            @ApiResponse(code = 404, message = "New with that id doesn't exist")})
    @ApiParam(value = "New id", required = true)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable(name = "id") Long id) {
        try {
            newsService.deleteById(id);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), NOT_FOUND);
        }
        return new ResponseEntity<>(messageSource.getMessage("news.deleted.message", null, Locale.US), OK);
    }

    @ApiOperation(value = "Find comment by new id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Comment found successfully"),
            @ApiResponse(code = 404, message = "New with that id doesn't exist")})
    @ApiParam(value = "New id", required = true)
    @GetMapping("/{newsId}/comments")
    public ResponseEntity<?> findCommentByNewsId(@PathVariable("newsId") Long newsId){
     List<CommentDto> commentsDto; 
        try {
            commentsDto = commentServiceImpl.findCommentByNewsId(newsId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), NOT_FOUND);
        }
     return ResponseEntity.status(OK).body(commentsDto);
    }

}
