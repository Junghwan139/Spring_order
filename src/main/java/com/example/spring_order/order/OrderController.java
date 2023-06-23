package com.example.spring_order.order;

import com.example.spring_order.item.Item;
import com.example.spring_order.item.ItemService;
import com.example.spring_order.item.ItemDto;
import com.example.spring_order.member.MemberService;
import com.example.spring_order.orderdetail.OrderItemService;
import com.example.spring_order.orderdetail.Order_Item;
import com.example.spring_order.orderdetail.Order_ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    @Autowired MemberService memberService;
    @Autowired ItemService itemService;
    @Autowired OrderService orderService;
    @Autowired OrderItemService orderItemService;


    @GetMapping("order")
    public String order_new(Model model1, Model model2){
        model1.addAttribute("items", itemService.item_findall());
        model2.addAttribute("members", memberService.member_findall());

        return "order/orderForm";

    }


    @PostMapping("order")
    public String order_regist(OrderDto order, Order_ItemDto orderItemDto) throws Exception {

        // Customer_Order저장 → Order_Item 저장
        orderService.order_save(order);

        // 주문수량 만큼 Item수량 마이너스
        Item item = itemService.item_one(Long.parseLong(order.getItemId()));

        // 업데이트는 ItemDto객체로 넘겨줘야 해서 새로 생성
        ItemDto item1 = new ItemDto();
        item1.setId(Long.parseLong(order.getItemId()));
        item1.setName(item.getName());
        item1.setPrice(item.getPrice());
        item1.setStockQuantity(item.getStockQuantity()-order.getCount());
        itemService.update(item1);
        return "redirect:/";

    }

//    @GetMapping("orders")
//    public String orderList(Model model){
//        model.addAttribute("orders",orderService.order_find_all());
//       // List<customer_Order> orderList = orderService.order_find_all();
//        return "order/orderList";
//    }




}
