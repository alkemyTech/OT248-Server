package com.alkemy.ong.controller;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.exception.ApiError;
import com.alkemy.ong.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping()
    public ResponseEntity<?> getAllMembers(){
        List<MemberDto> dtoList = memberService.getAll();
        if(dtoList == null  || dtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(messageSource.getMessage( "error.memberList.empty", null, Locale.US));
        }
        return ResponseEntity.ok().body(dtoList);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody MemberDto memberDto){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(memberService.create(memberDto));
        } catch (Exception e){
            throw new ApiError(HttpStatus.BAD_REQUEST, e);
        }
    }

    @PostMapping("{id}")
    public ResponseEntity<?> removeMember(@PathVariable(value = "id") Long id) {
        try {
            memberService.removeMember(id);
            return ResponseEntity
                    .ok()
                    .body(messageSource.getMessage("deleted.member", null, Locale.US));
        } catch (Exception exception) {
            throw new ApiError(HttpStatus.NOT_FOUND, exception);
        }
    }
}