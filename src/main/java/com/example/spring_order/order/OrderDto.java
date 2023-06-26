package com.example.spring_order.order;

import com.example.spring_order.orderdetail.Order_Item;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class OrderDto {

    private Long id;
    private List<Long> count;
    private String status;
    private String memberId;
    private List<String> itemId;
    private List<Order_Item> orderItems;



}
