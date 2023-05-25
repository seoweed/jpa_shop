package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemberRepositoryTest {
//    @Autowired MemberRepository memberRepository;
//    @Test
//    @Transactional // em 은 transactional 안에서 동작해야 한다.
//    // 테스트 안에 @Transactional 이 있으면 쿼리 날린 것들을 rollback 한다.
//    // 다른 일반적인 class 에서는 정상 동작함
//    @Rollback(false)
//    public void testMember() throws Exception {
//        // given
//        Member member = new Member();
//        member.setUsername("memberA");
//        // when
//        Long findId = memberRepository.save(member);
//        Member findMember = memberRepository.find(findId);
//
//        // then
//        assertThat(findMember.getId()).isEqualTo(member.getId());
//        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
//        assertThat(findMember).isEqualTo(member);
//    }

}