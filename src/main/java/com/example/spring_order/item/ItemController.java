package com.example.spring_order.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ItemController {


    @Autowired
    ItemService itemService;

    @GetMapping("items/new")
    public String item_new(Model model) {
        model.addAttribute("form", new form());
        return "Items/createItemForm";
    }

    @PostMapping("items/new")
    public String member_new_post(form item) {
        itemService.item_save(item);
        return "redirect:/";

    }

    @GetMapping("items")
    public String itemlist(Model model) throws SQLException {

        List<Item> items = itemService.item_findall();
        model.addAttribute("items", items);
        return "items/itemList";

    }



    @GetMapping("items/{id}/edit")
    public String item_update(form item, Model model) {

        // get요청의 parameter 넣는 방법 2가지 1)pathvariable 2)RequestParam(form을 쓰는 방법)

        Item item1 = itemService.item_one(item.getId());
        item.setName(item1.getName());
        item.setPrice(item1.getPrice());
        item.setStockQuantity(item1.getStockQuantity());

        model.addAttribute("name", item.getName());
        model.addAttribute("price", item.getPrice());
        model.addAttribute("stockQuantity", item.getStockQuantity());

        return "Items/updateItemForm";
    }



    @PostMapping("items/{id}/edit")
    public String itemUpdate(form item) throws Exception {
        itemService.update(item);
        return "redirect:/items";

    }


    //get요청 : request header 요청 보이고
    // post요청 : request body 요청 안보이고



}