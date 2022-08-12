package com.alkemy.ong.service.mapper;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.model.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public MemberDto memberToDto(Member member){
        return MemberDto.builder()
                .id(member.getId())
                .name(member.getName())
                .facebookUrl(member.getFacebookUrl())
                .instagramUrl(member.getInstagramUrl())
                .linkedinUrl(member.getLinkedinUrl())
                .description(member.getDescription())
                .image(member.getImage())
                .build();
    }

    public Member dtoToEntity(MemberDto memberDto){
        return Member.builder()
                .id(memberDto.getId())
                .name(memberDto.getName())
                .facebookUrl(memberDto.getFacebookUrl())
                .instagramUrl(memberDto.getInstagramUrl())
                .linkedinUrl(memberDto.getLinkedinUrl())
                .description(memberDto.getDescription())
                .image(memberDto.getImage())
                .build();
    }
}
