package com.alkemy.ong.controller;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.exception.ApiError;
import com.alkemy.ong.service.MemberService;
import com.alkemy.ong.service.impl.MemberServiceImpl;
import com.alkemy.ong.util.MemberResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import static com.alkemy.ong.util.Constants.*;


@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/pagination")
    public MemberResponse listMembers(
            @RequestParam(value = "pageNo", defaultValue = NUMBER_PAGE_DEFAULT, required = false) int numberPage,
            @RequestParam(value = "pageSize", defaultValue = SIZE_PAGE_DEFAULT, required = false) int sizePage,
            @RequestParam(value = "sortBy", defaultValue = ORDER_BY_DEFAULT, required = false) String orderBy,
            @RequestParam(value = "sortDir", defaultValue = ORDER_DIRECTION_DEFAULT, required = false) String sortDir) {
        return memberService.getAllMemberWithPagination(numberPage, sizePage, orderBy, sortDir);
    }

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


    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember (@Valid @PathVariable(value = "id") Long id, @RequestBody MemberDto memberUpdate ) {
        MemberDto memberDtoResponse = memberService.updateMember(memberUpdate,id);
        if(memberDtoResponse==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Member "+messageSource.getMessage
                            ("error.memberList.empty", null, Locale.US));
        }
        return ResponseEntity.status(HttpStatus.OK).body(memberDtoResponse);
    }


    @DeleteMapping("{id}")
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

