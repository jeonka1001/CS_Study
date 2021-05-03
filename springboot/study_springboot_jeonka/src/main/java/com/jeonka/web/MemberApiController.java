package com.jeonka.web;

import com.jeonka.service.member.MemberService;
import com.jeonka.web.member.dto.MemberSaveRequestDto;
import com.jeonka.web.member.dto.MemberUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/api/member")
    public Long save(@RequestBody MemberSaveRequestDto requestDto){
        return memberService.save(requestDto);
    }

    @PutMapping("/api/member/{id}")
    public Long update(@PathVariable Long id, @RequestBody MemberUpdateRequestDto requestDto){
        return memberService.update(id,requestDto);
    }
}
