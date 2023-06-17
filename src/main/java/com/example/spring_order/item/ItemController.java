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


    @Autowired ItemService itemService;

    @GetMapping("items/new")
    public String item_new(Model model){
        model.addAttribute("form",new form());
        return "Items/createItemForm";
    }

    @PostMapping("items/new")
    public String member_new_post(form item)
    {
        itemService.item_save(item);
        return "redirect:/";

    }

    @GetMapping("items")
    public String itemlist(Model model) throws SQLException {

        List<Item> items = itemService.item_findall();
        model.addAttribute("items",items);
        return "items/itemList";

    }



    @GetMapping("items/{id}/edit")
    public String item_update(form item, Model model){
        model.addAttribute("form",item);
        return "Items/updateItemForm";
    }


//    @PostMapping()
//    public String itemUpdate(form item)  throws Exception {
//        itemService.update(item);
//        return "redirect:/items/itemList";



}
