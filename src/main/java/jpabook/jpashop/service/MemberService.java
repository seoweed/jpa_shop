package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// jpa 의 모든 데이터 변경이나 로직들은 Transactional 안에서 실행되어야 한다.
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원 가입
    public Long join(Member member) {
        // 중복회원 검증
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }
    // 중복회원 검증 메서드
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findAll() {
        return memberRepository.findAll();
    }
    // 회원 1건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


}
