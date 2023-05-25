package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Test
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long memberId = memberService.join(member);

        // then
        assertThat(member).isEqualTo(memberRepository.findOne(memberId));

    }
    @Test
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("memberA");
        member2.setName("memberA");

        // when
        memberService.join(member1);
        try {
            memberService.join(member1); // 예외 발생 해야함

        } catch (IllegalStateException e) {
            return;
        }
        // then
        fail("예외가 발생해야 한다.");
    }

}