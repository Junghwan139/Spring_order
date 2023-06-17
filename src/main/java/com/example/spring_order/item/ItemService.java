package com.example.spring_order.item;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ItemService {

    @Autowired ItemRepository itemRepository;

    public void item_save(form item){

        Item item1 = Item.builder()
                .name(item.getName())
                .price(item.getPrice())
                .stockQuantity(item.getStockQuantity())
                .build();
        itemRepository.save(item1);

    }

    public List<Item> item_findall(){
        return itemRepository.findAll();
    }

    public Item item_one(Long id){
        return itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


}
