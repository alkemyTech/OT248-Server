package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.repository.MemberRepository;
import com.alkemy.ong.service.MemberService;
import com.alkemy.ong.service.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public MemberDto create(MemberDto memberDto) {
        Member member = memberMapper.dtoToEntity(memberDto);
        Member newMember = memberRepository.save(member);
        return memberMapper.memberToDto(newMember);
    }

    @Override
    public List<MemberDto> getAll() {
        return memberRepository.findAll().stream()
                .map(m -> memberMapper.memberToDto(m))
                .collect(Collectors.toList());
    }

    @Override
    public MemberDto findMemberById(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if(member.isEmpty())return null;
        return memberMapper.memberToDto(member.get());
    }

    @Override
    public MemberDto updateMember(MemberDto memberUpdate, Long id) {
        MemberDto memberDto = findMemberById(id);
        if(memberDto==null) return null;
        memberUpdate.setId(id);
        Member member = memberMapper.dtoToEntity(memberUpdate);
        return memberMapper.memberToDto(memberRepository.save(member));
    }



}

