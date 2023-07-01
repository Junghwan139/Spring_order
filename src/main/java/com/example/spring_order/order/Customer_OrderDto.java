package com.example.spring_order.order;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Customer_OrderDto {

    private Long id;
    private List<Long> count;
    private List<String> itemId;
    private String status;
    private String memberId;


}
