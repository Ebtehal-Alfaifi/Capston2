package com.example.maintaincesystem.Repository;

import com.example.maintaincesystem.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    Order findOrderByOrderId(Integer orderId);

    List<Order> findAllByOrderIdAndStatus(Integer orderId, String status);

}
