package com.example.spring_order.member;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberRequestDto {

    public Long id;
    private String name;
    private String email;
    private String password;
    private String city;
    private String address;
    private String address_code;



}
