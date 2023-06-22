package com.example.spring_order.orderdetail;

import com.example.spring_order.item.Item;
import com.example.spring_order.order.Customer_Order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order_ItemDto {


    private Long orderPrice;
    private Long count;
    private Item itemId;
    private Customer_Order customerOrder;


}
