package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor

public class ItemRepository {
//    원래 em 을 가져오기 위해서는 @PersistenceContext 가 필요하지만
//  @Autowired 스프링 부트가 자동으로 주입을 해준다. 따라서 롬복 생성자를 사용하면 em 을 편하게 가져올수 있다.

    private final EntityManager em;
    // 상품 저장
    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
