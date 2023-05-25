package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {
    @PersistenceContext // 스프링이 만들어둔 EntityManager 를 주입받을 때 사용함
    private EntityManager em;

    // 저장 로직
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }
    // 하나 조회하는 로직
    public Member find(Long id) {
        return em.find(Member.class, id);
    }



}
