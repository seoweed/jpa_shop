package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;

    // 저장 로직
    public void save(Member member) {
        em.persist(member);
    }
    // 조회 로직
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }
    // 전체 조회
    public List<Member> findAll() {
        // jpql 과 sql 의 차이점
        // sql: table 을 대상으로 쿼리를 날림, jpql: 엔티티 객체를 대상으로 쿼리를 날림
        List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return result;
    }
    // 이름으로 조회
    public List<Member> findByName(String name) {
        // :name 은 파라미터 바인딩, setParameter 로 파라미터를 설정하고 getResultList 로 결과를 받아온다.
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }


}
