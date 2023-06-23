package com.example.spring_order.order;


import com.example.spring_order.item.Item;
import com.example.spring_order.member.Member;
import com.example.spring_order.orderdetail.OrderItemService;
import com.example.spring_order.orderdetail.Order_Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Customer_Order {


    //  주문(order) : id, member_id, item_id, 주문수량, 주문상태, item, member
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long count;

    private String status;

    private LocalDateTime orderDate;

    //Item FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name="item_id")
    private Item item;

    // Member FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name="member_id")
    private Member member;

    // Order_Item FK
    @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL)
    private List<Order_Item> orderItems = new ArrayList<>();


    @Builder
    public Customer_Order(Long count, String status, Item item1, Member member1){

        this.count = count;
        this.status = status;
        this.item = item1;
        this.member = member1;
        this.orderDate = LocalDateTime.now();

    }


}
