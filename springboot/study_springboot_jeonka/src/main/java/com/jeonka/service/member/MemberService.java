package com.jeonka.service.member;

import com.jeonka.domain.member.Member;
import com.jeonka.domain.member.MemberRepository;
import com.jeonka.web.member.dto.MemberSaveRequestDto;
import com.jeonka.web.member.dto.MemberUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long save(MemberSaveRequestDto requestDto){
        return memberRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id,MemberUpdateRequestDto requestDto){
        Member entity = memberRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 회원이 존재하지 않습니다. id="+id)
        );

        entity.update(requestDto.getEmail(),requestDto.getNickName(), requestDto.getPassword());

        return id;
    }

    @Transactional
    public Long delete(Long id){
        memberRepository.delete(id);
    }
}
