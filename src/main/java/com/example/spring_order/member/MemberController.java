package com.example.spring_order.member;

import com.example.spring_order.order.Customer_Order;
import com.example.spring_order.order.Customer_OrderRepository;
import com.example.spring_order.order.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MemberController {

    @Autowired MemberService memberService;
    @Autowired
    Customer_OrderRepository customerOrderRepository;
    @Autowired MemberRepository memberRepository;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("members/new")
    public String member_new(Model model){
        // createMemberForm에서 memberForm이라는 Dto 객체를 필요로 하므로, dto객체를 만들어서 model을 통해 전달
        // DTO에서 NotNull, NotEmpty등 validation 처리를 할 수가 있고, 해당 객체를 createMemberForm화면에 전달함으로서
        // 화면에서 validation체크를 할 수가 있다.
        model.addAttribute("memberForm",new MemberRequestDto());
        return "members/createMemberForm";
    }


    @PostMapping("members/new")
    public String member_new_post(MemberRequestDto member)
    {
        memberService.member_save(member);
        return "redirect:/members";

    }

    @GetMapping("members")
    public String memberFindAll(Model model) throws SQLException {   //타서버 조회시 강제시킴

        List<Member> members = memberService.member_findall() ;

        List<MemberResponseDto> memberResponseDtos1 = new ArrayList<>();

        for(Member a : members) {
            //ResponseDto입력
            MemberResponseDto memberResponseDtos = new MemberResponseDto();
            memberResponseDtos.setId(a.getId());
            memberResponseDtos.setName(a.getName());
            memberResponseDtos.setEmail(a.getEmail());
            memberResponseDtos.setCity(a.getAddress().getCity());
            memberResponseDtos.setStreet(a.getAddress().getStreet());
            memberResponseDtos.setZipcode(a.getAddress().getZipcode());
            memberResponseDtos.setOrdercount(Long.parseLong(String.valueOf(a.getCustomerOrders().size())));
            memberResponseDtos1.add(memberResponseDtos);

        }

        model.addAttribute("members",memberResponseDtos1);



        // 주문수량 주문금액 합계
        for(Member a : members){
            // 고객이 주문한 오더를 꺼냄
            List<Customer_Order> customerOrders = customerOrderRepository.findByMember(a);

            int total = 0;

            // 오더에서 캔슬이 안된 주문을 꺼냄
            for(Customer_Order b : customerOrders ){
                if(b.getStatus() != OrderStatus.CANCELED){

                    // 주문별로 구매된 아이템을 꺼내고 합을 구함
                    for(int i = 0;i<b.getOrderItems().size();i++){
                        total += b.getOrderItems().get(i).getOrderPrice().intValue() * b.getOrderItems().get(i).getCount().intValue();
                    }
                }
            }
            System.out.println(a.getName() +"의 주문금액은 : "+total);
        }




        return "members/memberList";

    }



}
