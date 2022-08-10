package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.dto.response.CommentResponseDTO;
import com.alkemy.ong.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired private ICommentService iCommentService;

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
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(iCommentService.getAllResponseDto());
    }
}
