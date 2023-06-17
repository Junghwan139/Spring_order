package com.example.spring_order.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {


    @Autowired ItemService itemService;

    @GetMapping("items/new")
    public String item_new(Model model){
        model.addAttribute("form",new form());
        return "Items/createItemForm";
    }


}
