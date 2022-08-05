package com.alkemy.ong.service;

import com.alkemy.ong.dto.MemberDto;

import java.util.List;

public interface MemberService {

    public MemberDto create(MemberDto memberDto);
    List<MemberDto> getAll();

}
