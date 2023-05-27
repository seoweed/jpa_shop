package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class OrderServiceTest {
    @Autowired OrderService orderService;
    @Autowired EntityManager em;
    @Autowired OrderRepository orderRepository;
    @Test
    public void 상품주문() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);

        Book book = new Book();
        book.setStockQuantity(10);
        book.setName("jpaBook");
        book.setPrice(10000);
        em.persist(book);

        int orderCount = 2;
        // when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        // then
        Order getOrder = orderRepository.findOne(orderId);
        assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        assertThat(1).isEqualTo(getOrder.getOrderItems().size());
        assertThat(10000 * 2).isEqualTo(getOrder.getTotalPrice());
        assertThat(8).isEqualTo(book.getStockQuantity());

    }
    @Test
    public void 상품주문_재고수량초과() throws Exception {
        Member member = new Member();
        member.setName("kim");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);

        Book book = new Book();
        book.setStockQuantity(2);
        book.setName("jpaBook");
        book.setPrice(10000);
        em.persist(book);

        int orderCount = 3;
        // when
        try {
            orderService.order(member.getId(), book.getId(), orderCount);
        } catch (NotEnoughStockException e) {
            return;
        }

        // then
        Assertions.fail("이 에러 메시지가 발생하면 테스트 실패입니다.");
    }
    @Test
    public void 주문취소() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);

        Book book = new Book();
        book.setStockQuantity(10);
        book.setName("jpaBook");
        book.setPrice(10000);
        em.persist(book);

        int orderCount = 2;
        // when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        orderService.cancelOrder(orderId);
        // then
        Order getOrder = orderRepository.findOne(orderId);
        assertThat(OrderStatus.CANCEL).isEqualTo(getOrder.getStatus());
        assertThat(10).isEqualTo(book.getStockQuantity());
    }


}