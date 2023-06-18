package com.example.spring_order.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {


    private Long id;
    private Long count;
    private String status;
    private String memberId;
    private String itemId;



}
