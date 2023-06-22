package com.example.spring_order.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Member {

    @Id  // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GeneratedValue -> auto_Incerement
    private Long id;

    private String name;

    private String email;

    private String password;

    @Embedded
    private Address address;

    @Builder
    public Member(String name, String email, String password, Address address){
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;


    }



}
