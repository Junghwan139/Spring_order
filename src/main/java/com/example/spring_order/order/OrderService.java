package com.example.spring_order.order;

import com.example.spring_order.item.ItemService;
import com.example.spring_order.member.MemberService;
//import com.example.spring_order.orderdetail.OrderItemService;
import com.example.spring_order.orderdetail.OrderItemService;
import com.example.spring_order.orderdetail.Order_ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OrderService {

    @Autowired OrderRepository orderRepository;
    @Autowired MemberService memberService;
    @Autowired ItemService itemService;
    @Autowired OrderItemService orderItemService;

    //Create
    public void order_save(OrderDto order){

        Customer_Order order1 = Customer_Order.builder()
                        .count(order.getCount())
                        .status("ORDER")
                        .item1(itemService.item_one(Long.parseLong(order.getItemId())))
                        .member1(memberService.find_one(Long.parseLong(order.getMemberId())))
                        .build();

        //
        Order_ItemDto orderItemDto = new Order_ItemDto();
        orderItemDto.setCustomerOrder(order1);
        orderItemDto.setOrderPrice(order1.getItem().getPrice());
        orderItemDto.setCount(order1.getCount());
        orderItemDto.setItemId(order1.getItem());
        orderItemService.orderItemSave(orderItemDto);

        //
        order1.setOrderItems(orderItemService.order_find_all());

        orderRepository.save(order1);

    }

    // read_all
    public List<Customer_Order> order_find_all(){
        return orderRepository.findAll();
    }

    public Customer_Order order_find_one(Long id){
        return orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }










}
