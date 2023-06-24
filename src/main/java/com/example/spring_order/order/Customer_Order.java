package com.example.spring_order.order;


import com.example.spring_order.item.Item;
import com.example.spring_order.member.Member;
import com.example.spring_order.orderdetail.Order_Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Customer_Order {


    //  주문(order) : id, member_id, item_id, 주문수량, 주문상태, item, member

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long count;  // 주문수량

    private String status;  // 상태

    private LocalDateTime orderDate;  // 주문일자

    //Item FK     상품명 -> item_id로 대체가능
    @ManyToOne(fetch = FetchType.LAZY)    // @OneToOne    1:N -> author와 post(FK), 1:1 ->
    @JoinColumn(nullable = false, name="item_id") // FK 아무쪽에나 걸 수 있으나, 거는 쪽에 Id가 걸림
    private Item item;


    // Member FK       member와 orders와 관계, orders findById조회 -> orders에 컬럼중에 member_id가 있네?
    // ManyToOne걸려 있네? member_id로 member테이블 조회
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name="member_id")
    private Member member;


    // Order_Item 조회해오기
    @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL)
    private List<Order_Item> orderItems = new ArrayList<>();


    // status_change
    public void status_Change(){
        this.status = "CANCELED";
    }


    @Builder
    public Customer_Order(Long count, String status, Item item1, Member member1){

        this.count = count;
        this.status = status;
        this.item = item1;
        this.member = member1;
        this.orderDate = LocalDateTime.now();

    }


}
