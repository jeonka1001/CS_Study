package com.jeonka.domain.posts;

import com.jeonka.domain.member.Member;
import com.jeonka.domain.member.MemberRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @After
    public void cleanup(){ memberRepository.deleteAll();}

    @Test
    public void 회원가입_작동테스트(){
        String email = "ruddks2009@naver.com";
        String nickName = "jeonka";
        String password = "1234";

        memberRepository.save(Member.builder().email(email).nickName(nickName).password(password).build());

        List<Member> all = memberRepository.findAll();
        Member member = all.get(0);
        assertThat(member.getEmail()).isEqualTo(email);
        assertThat(member.getNickName()).isEqualTo(nickName);
        assertThat(member.getPassword()).isEqualTo(password);
    }

}
