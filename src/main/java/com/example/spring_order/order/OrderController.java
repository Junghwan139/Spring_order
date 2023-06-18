package com.example.spring_order.order;

import com.example.spring_order.item.Item;
import com.example.spring_order.item.ItemService;
import com.example.spring_order.item.form;
import com.example.spring_order.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {

    @Autowired MemberService memberService;
    @Autowired ItemService itemService;
    @Autowired OrderService orderService;


    @GetMapping("order")
    public String order_new(Model model1, Model model2){
        model1.addAttribute("items", itemService.item_findall());
        model2.addAttribute("members", memberService.member_findall());

        return "order/orderForm";

    }


    @PostMapping("order")
    public String order_regist(OrderDto order) throws Exception {
        orderService.order_save(order);
        Item item = itemService.item_one(Long.parseLong(order.getItemId()));
        form item1 = new form();
        item1.setId(Long.parseLong(order.getItemId()));
        item1.setName(item.getName());
        item1.setPrice(item.getPrice());
        item1.setStockQuantity(item.getStockQuantity()-order.getCount());
        itemService.update(item1);
        return "redirect:/orders";

    }

    @GetMapping("orders")
    public String orderList(Model model){
        
        return "order/orderList";
    }









}
