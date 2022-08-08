package com.alkemy.ong.service;

import com.alkemy.ong.dto.MemberDto;
import java.util.List;

public interface MemberService {

    MemberDto create(MemberDto memberDto);
    
    List<MemberDto> getAll();
    void removeMember(Long id);
}