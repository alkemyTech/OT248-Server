package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.repository.MemberRepository;
import com.alkemy.ong.service.MemberService;
import com.alkemy.ong.service.mapper.MemberMapper;
import com.alkemy.ong.util.MemberResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public MemberResponse getAllMemberWithPagination(int numPage, int sizePage, String orderBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending()
                : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage, sizePage, sort);

        Page<Member> members = memberRepository.findAll(pageable);

        List<Member> categoryList = members.getContent();
        List<MemberDto> content = categoryList.stream().map(member -> memberMapper.memberToDto(member))
                .collect(Collectors.toList());

        MemberResponse memberResponse = new MemberResponse();
        memberResponse.setContent(content);
        memberResponse.setNumPage(members.getNumber());
        memberResponse.setSizePage(members.getSize());
        memberResponse.setTotalElements(members.getTotalElements());
        memberResponse.setTotalPages(members.getTotalPages());
        memberResponse.setFirstPage(members.isFirst());
        memberResponse.setLastPage(members.isLast());
        memberResponse.setNextPage(members.nextOrLastPageable());
        memberResponse.setPreviousPage(members.previousOrFirstPageable());

        return memberResponse;
    }

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


    private MemberDto findMemberById(Long id) {
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


    @Override
    public void removeMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member", "id", id));
        memberRepository.delete(member);
    }
}

