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
    private String email;

    @Setter
    @Column(length = 255)
    private String password;

    @Column(length = 255)
    private String city;

    @Column(length = 255)
    private String street;


    @Column(length = 255)
    private String zipcode;




    //    생성자 방식과 builder패턴
    @Builder
    public Item(String name, String email, String password, String city, String street, String zipcode){
        this.name = name;
        this.email = email;
        this.password = password;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;

    }



}