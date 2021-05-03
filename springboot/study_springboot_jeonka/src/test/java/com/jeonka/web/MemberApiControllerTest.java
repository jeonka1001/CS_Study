package com.jeonka.web;


import com.jeonka.domain.member.Member;
import com.jeonka.domain.member.MemberRepository;
import com.jeonka.web.member.dto.MemberSaveRequestDto;
import com.jeonka.web.member.dto.MemberUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testTemplate;

    @Autowired
    private MemberRepository memberRepository;

    @After
    public void cleanUp()throws Exception{
        memberRepository.deleteAll();
    }

    @Test
    public void Member_등록테스트()throws Exception{
        String email = "ruddks2009@naver.com";
        String password = "1234";
        String nickName = "jeonka";

        MemberSaveRequestDto requestDto = MemberSaveRequestDto.builder()
                .email(email)
                .password(password)
                .nickName(nickName)
                .build();

        String url = "http://localhost:"+port+"/api/member";

        ResponseEntity<Long> responseEntity = testTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Member> all = memberRepository.findAll();
        Member member = all.get(0);

        assertThat(member.getEmail()).isEqualTo(email);
        assertThat(member.getPassword()).isEqualTo(password);
        assertThat(member.getNickName()).isEqualTo(nickName);

    }

    @Test
    public void Member_수정테스트(){
        Member saveMember = memberRepository.save(Member.builder().email("email").nickName("nickName").password("password").build());
        String email = "ruddks1001@naver.com";
        String password = "1234";
        String nickName = "jeonka";
        Long saveId = saveMember.getId();

        MemberUpdateRequestDto requestDto = MemberUpdateRequestDto.builder().email(email).password(password).nickName(nickName).build();

        String uri = "http://localhost:"+port+"/api/member/"+saveId;

        HttpEntity<MemberUpdateRequestDto> httpEntity = new HttpEntity<>(requestDto);

        ResponseEntity<Long> responseEntity = testTemplate.exchange(uri, HttpMethod.PUT, httpEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Member> all = memberRepository.findAll();
        Member member = all.get(0);

        assertThat(member.getEmail()).isEqualTo(email);
        assertThat(member.getNickName()).isEqualTo(nickName);
        assertThat(member.getPassword()).isEqualTo(password);

    }
}
