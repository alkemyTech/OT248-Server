package com.alkemy.ong.controller;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.exception.ApiError;
import com.alkemy.ong.service.impl.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberServiceImpl memberServiceImpl;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody MemberDto memberDto){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(memberServiceImpl.create(memberDto));
        } catch (Exception e){
            throw new ApiError(HttpStatus.BAD_REQUEST, e);
        }
    }
}
