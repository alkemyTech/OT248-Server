package com.alkemy.ong.service;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.util.MemberResponse;

import java.util.List;

public interface MemberService {

    MemberResponse getAllMemberWithPagination(int numPage, int sizePage, String orderBy, String sortDir);
    MemberDto create(MemberDto memberDto);
    
    List<MemberDto> getAll();
    void removeMember(Long id);
}