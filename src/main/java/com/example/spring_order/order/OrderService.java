package com.example.spring_order.order;


import com.example.spring_order.item.ItemService;
import com.example.spring_order.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OrderService {

    @Autowired OrderRepository orderRepository;
    @Autowired MemberService memberService;
    @Autowired ItemService itemService;

    //Create
    public void order_save(OrderDto order){

        customer_Order order1 = customer_Order.builder()
                        .count(order.getCount())
                        .status("ORDER")
                        .item1(itemService.item_one(Long.parseLong(order.getItemId())))
                        .member1(memberService.find_one(Long.parseLong(order.getMemberId())))
                        .build();
        orderRepository.save(order1);
    }

    // read_all
    public List<customer_Order> orderList(){
        return orderRepository.findAll();
    }

    public customer_Order order_one(Long id){
        return orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }










}
