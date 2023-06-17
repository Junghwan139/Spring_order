package com.example.spring_order.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @Setter
    @Id  // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GeneratedValue -> auto_Incerement
    private Long id;

    @Setter
    @Column(length = 50)
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
    private String address;


    @Column(length = 255)
    private String address_code;




    //    생성자 방식과 builder패턴
    @Builder
    public Member(String name, String email, String password, String city, String address, String address_code){
        this.name = name;
        this.email = email;
        this.password = password;
        this.city = city;
        this.address = address;
        this.address_code = address_code;

    }



}
