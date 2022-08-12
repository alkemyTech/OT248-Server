package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.dto.response.UserResponseDTO;
import com.alkemy.ong.exception.ApiError;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.dto.response.CommentResponseDTO;
import com.alkemy.ong.service.ICommentService;
import com.alkemy.ong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;
import java.util.Objects;
import java.util.List;
import java.util.Locale;


@RestController
@RequestMapping("/comments")
public class CommentController {


    @Autowired private ICommentService commentService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<CommentDto> addComment(
            @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.save(commentDto), HttpStatus.CREATED);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> removeComment(@PathVariable(value = "id") Long id) {
        try {
            Authentication userCommentIdObject = SecurityContextHolder.getContext().getAuthentication();
            boolean hasAdminRole = userCommentIdObject.getAuthorities().stream()
                    .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
            Long commentId= commentService.findById(id).getUserId();
            String name = userCommentIdObject.getName();
            Long userId =userService.findByEmail(name).getId();
            if (hasAdminRole)
            {
                commentService.deleteById(id);
                return ResponseEntity.ok()
                        .body(messageSource.getMessage("deleted.comment", null, Locale.US));
            }
            if (!Objects.equals(userId, commentId)){
                return new ResponseEntity<>((messageSource.getMessage("notallowed.permission", null, Locale.US)), HttpStatus.FORBIDDEN);
            }
            commentService.deleteById(id);
            return ResponseEntity.ok()
                    .body(messageSource.getMessage("deleted.comment", null, Locale.US));
        } catch (Exception exception) {
            throw new ApiError(HttpStatus.NOT_FOUND, exception);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<CommentResponseDTO> response = commentService.getAllResponseDto();
        if(response == null || response.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(messageSource.getMessage("error.commentList.empty", null, Locale.US));
        }
        return ResponseEntity.ok(commentService.getAllResponseDto());
    }

}
