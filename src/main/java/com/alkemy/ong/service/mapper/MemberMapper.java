package com.alkemy.ong.service.mapper;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.model.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public MemberDto MemberToDto(Member member){
        return MemberDto.builder()
                .name(member.getName())
                .facebookUrl(member.getFacebookUrl())
                .instagramUrl(member.getInstagramUrl())
                .linkedinUrl(member.getLinkedinUrl())
                .description(member.getDescription())
                .image(member.getImage())
                .build();
    }

    public Member DtoToEntity(MemberDto memberDto){
        return Member.builder()
                .name(memberDto.getName())
                .facebookUrl(memberDto.getFacebookUrl())
                .instagramUrl(memberDto.getInstagramUrl())
                .linkedinUrl(memberDto.getLinkedinUrl())
                .description(memberDto.getDescription())
                .image(memberDto.getImage())
                .build();
    }
}
