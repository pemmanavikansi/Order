package com.example.order.controller;

import com.example.order.bo.Order;
import com.example.order.bo.RequestPayload;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome to order service";
    }

    @PostMapping("/saveOrder")
    public RequestPayload saveOrder(@RequestBody RequestPayload requestPayload){
        return orderService.saveOrder(requestPayload);
    }

    @GetMapping("/getAll")
    public List<Order> getAllOrders(){
        return orderService.getOrders();
    }
}
