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
        Member member = memberMapper.DtoToEntity(memberDto);
        Member newMember = memberRepository.save(member);
        return memberMapper.MemberToDto(newMember);
    }

    @Override
    public List<MemberDto> getAll() {
        return memberRepository.findAll().stream()
                .map(m -> memberMapper.MemberToDto(m))
                .collect(Collectors.toList());
    }

    @Override
    public MemberDto findMemberById(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        return memberMapper.MemberToDto(member.get());
    }

    @Override
    public MemberDto updateMember(MemberDto memberUpdate, Long id) {
        try {
            MemberDto memberDto = findMemberById(id);
        } catch (NoSuchElementException exception) {
            return null;
        }
        memberUpdate.setId(id);
        Member member = memberMapper.DtoToEntity(memberUpdate);
        return memberMapper.MemberToDto(memberRepository.save(member));
    }



}

