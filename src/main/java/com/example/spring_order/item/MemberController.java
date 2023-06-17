package com.example.spring_order.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("members/new")
    public String member_new(Model model){
        model.addAttribute("memberForm",new MemberForm());
        return "members/createMemberForm";
    }


    @PostMapping("members/new")
    public String member_new_post(MemberForm member)
    {
        memberService.member_save(member);
        return "redirect:/";

    }

    @GetMapping("members")
    public String memberFindAll(Model model) throws SQLException {   //타서버 조회시 강제시킴

        List<Member> members = memberService.member_findall() ;
        model.addAttribute("members",members);
        return "members/memberList";

    }



















}
