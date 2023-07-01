package com.example.spring_order.orderdetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Order_ItemRepository extends JpaRepository<Order_Item, Long> {

    List<Order_Item> findByCustomerOrderId(Long id);

}
