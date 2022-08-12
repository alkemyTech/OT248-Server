package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.dto.response.CommentResponseDTO;
import com.alkemy.ong.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired private ICommentService iCommentService;
    @Autowired
    private MessageSource messageSource;

    @PostMapping
    public ResponseEntity<CommentDto> addComment(
            @Valid
            @RequestBody CommentDto commentDto
    ){
        return new ResponseEntity<>(iCommentService.save(commentDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<CommentResponseDTO> response = iCommentService.getAllResponseDto();
        if(response == null || response.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(messageSource.getMessage("error.commentList.empty", null, Locale.US));
        }
        return ResponseEntity.ok(iCommentService.getAllResponseDto());
    }
}
