package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
// @?ToOne 은 기본 fetch 가 eager 이므로 지연(Lazy)로 바꿔주어야 한다. 중요@
@Entity
@Getter @Setter
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING) // enum type 사용할 때 꼭 string 을 해주어야 한다.
    private DeliveryStatus status;
}
