package com.example.spring_order.order;


import com.example.spring_order.item.Item;
import com.example.spring_order.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class customer_Order {


    //  주문(order) : id, member_id, item_id, 주문수량, 주문상태, item, member
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long count;

    private String status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name="item_id")
    private Item item_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name="member_id")
    private Member member_id;


    @Builder
    public customer_Order(Long count, String status, Item item1, Member member1){

        this.count = count;
        this.status = status;
        this.item_id = item1;
        this.member_id = member1;

    }


}
