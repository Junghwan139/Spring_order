package com.example.spring_order.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Item {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(length = 255)
    private String name;

    @Setter
    @Column(length = 50, unique = true)
    private Long price;

    @Setter
    @Column(length = 255)
    private Long stockQuantity;




    //    생성자 방식과 builder패턴
    @Builder
    public Item(String name, Long price, Long stockQuantity){
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;


    }



}