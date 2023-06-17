package com.example.spring_order.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {

    public Long id;
    private String name;
    private String email;
    private String password;
    private String city;
    private String street;
    private String zipcode;



}
