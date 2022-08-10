package com.alkemy.ong.service;

import com.alkemy.ong.dto.MemberDto;
import java.util.List;

public interface MemberService {

    MemberDto create(MemberDto memberDto);
    
    List<MemberDto> getAll();

    MemberDto findMemberById(Long id);

    MemberDto updateMember(MemberDto memberUpdate, Long id);

    void removeMember(Long id);
}

