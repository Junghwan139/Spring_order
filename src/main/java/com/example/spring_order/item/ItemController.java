package com.example.spring_order.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ItemController {


    @Autowired
    ItemService itemService;

    @GetMapping("items/new")
    public String item_new(Model model) {
        model.addAttribute("form", new ItemDto());
        return "Items/createItemForm";
    }

    @PostMapping("items/new")
    public String member_new_post(ItemDto item) {
        itemService.item_save(item);
        return "redirect:/";

    }

    @GetMapping("items")
    public String itemlist(Model model) throws SQLException {

        List<Item> items = itemService.item_findall();
        model.addAttribute("items", items);
        return "items/itemList";

    }


    @GetMapping("items/{id}/edit")   // ItemDto item 양식화면에 Dto뿌려줘야 함
    public String item_update(ItemDto item, @PathVariable("id")Long myid, Model model) {

//      get요청의 parameter 넣는 방법 2가지 1)pathvariable 2)RequestParam(form을 쓰는 방법)

        Item item1 = itemService.item_one(myid);
        item.setName(item1.getName());    // html에 form으로 되어 있어서 Domain으로 넘길 수 없음
        item.setPrice(item1.getPrice());
        item.setStockQuantity(item1.getStockQuantity());

        model.addAttribute("form", item);
        return "items/updateItemForm";
    }



    @PostMapping("items/{id}/edit")
    public String itemUpdate(ItemDto item) throws Exception {
        itemService.update(item);
        return "redirect:/items";

    }


    //get요청 : request header 요청 보이고
    // post요청 : request body 요청 안보이고



}