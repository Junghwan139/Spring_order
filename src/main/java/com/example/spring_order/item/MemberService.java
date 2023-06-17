package com.example.spring_order.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;


    // Create
    public void member_save(MemberForm member){

        Member member1 = Member.builder()
                .name(member.getName())
                .email(member.getEmail())
                .password(member.getPassword())
                .city(member.getCity())
                .street(member.getStreet())
                .zipcode(member.getZipcode())
                .build();

        memberRepository.save(member1);

    }

    // Read_all

    public List<Member> member_findall(){
        return memberRepository.findAll();
    }

    // Read_one
    public Member find_one(Long id){
        return memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }



}
