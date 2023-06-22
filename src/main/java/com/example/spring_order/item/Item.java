package com.example.spring_order.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long price;

    private Long stockQuantity;

    @Builder
    public Item(String name, Long price, Long stockQuantity){
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;


    }



}