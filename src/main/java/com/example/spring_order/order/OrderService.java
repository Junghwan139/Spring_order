package com.example.spring_order.order;

import com.example.spring_order.item.ItemService;
import com.example.spring_order.member.MemberService;
import com.example.spring_order.orderdetail.OrderItemRepository;
import com.example.spring_order.orderdetail.Order_Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired OrderRepository orderRepository;
    @Autowired MemberService memberService;
    @Autowired ItemService itemService;
    @Autowired OrderItemRepository orderItemRepository;

    //Create
    public void order_save(OrderDto order) throws Exception {

        for(int i = 0;i<order.getCount().size();i++){

            // customer_order 저장
            Customer_Order order1 = Customer_Order.builder()
                    .count(order.getCount().get(i))
                    .status("ORDER")
                    .item1(itemService.item_one(Long.parseLong(order.getItemId().get(i))))
                    .member1(memberService.find_one(Long.parseLong(order.getMemberId())))
                    .build();
            orderRepository.save(order1);

            // order_item 저장
            Order_Item orderItem = Order_Item.builder()
                    .customerOrder(order1)
                    .orderPrice(order1.getItem().getPrice())
                    .count(order1.getCount())
                    .item(order1.getItem())
                    .build();
            orderItemRepository.save(orderItem);
        }


    }

    // read_all
    public List<Customer_Order> order_find_all(){
        return orderRepository.findAll();
    }

    // read_one
    public Customer_Order order_find_one(Long id){
        return orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    // update_status
    public void order_change_status(Long id){
        Customer_Order customerOrder = this.order_find_one(id);
        customerOrder.status_Change();
        // 아이템 재고량 + 시킴
        customerOrder.getItem().AddQuantity(customerOrder.getCount());
        orderRepository.save(customerOrder);

    }

    // delete
    public void order_delete(Long id){
        orderRepository.delete(this.order_find_one(id));
    }


    // read_search
    public List<Customer_Order> order_find_search(OrderSearch orderSearch){

        List<Customer_Order> orders = new ArrayList<>();

        if(isNullOrEmpty(orderSearch.getMemberName()) && orderSearch.getOrderStatus() == null){
           orders = this.order_find_all();

        } else if(isNullOrEmpty(orderSearch.getMemberName()) && orderSearch.getOrderStatus() != null){
            for(Customer_Order a : this.order_find_all()){
                if(a.getStatus() == orderSearch.getOrderStatus()){
                    orders.add(a);
                }
            }

        } else if(!isNullOrEmpty(orderSearch.getMemberName()) && orderSearch.getOrderStatus() == null){
            for(Customer_Order a : this.order_find_all()){
                if(a.getMember().getName().equals(orderSearch.getMemberName())){
                    orders.add(a);
                }
            }
        }

        else{    // 두개 이상의 컬럼으로 where 조건문을 걸떄는 and 포함
            for(Customer_Order a : this.order_find_all()){
                if(a.getMember().getName().equals(orderSearch.getMemberName()) && a.getStatus()==orderSearch.getOrderStatus()){
                    orders.add(a);
                }
            }
        }
        return orders;
    }

    private boolean isNullOrEmpty(String str){
        if(str == null){
            return true;
        }else if(str != null && str.isEmpty()){
            return true;
        }else{
            return false;
        }
    }


    // read_search
//    public List<Customer_Order> order_find_filter(OrderSearch orderSearch) {
//
//   if(orderSearch.getMemberName())  == null && ordersearchDTO.gerorderstatus()==null){
//    List<orders> orders = new arrist<>()
//      list<member> members = memberRepositoryfindbyname(ordersearchdto.getname()){
//        for(orders orders1:orderlist){
//            orders.add((orders1))
//        }
//    }

//    }







    }
