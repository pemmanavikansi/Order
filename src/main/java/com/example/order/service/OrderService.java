package com.example.order.service;

import com.example.order.bo.Order;
import com.example.order.bo.Payment;
import com.example.order.bo.RequestPayload;
import com.example.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    OrderRepository orderRepository;

    String uri = "http://PAYMENT/payment/doPayment";

    @Transactional
    public RequestPayload saveOrder(RequestPayload requestPayload){
        Order order = requestPayload.getOrder();
        Payment payment = restTemplate.postForObject(uri,requestPayload.getPayment(),Payment.class);
        order.setPaymentId(payment.getId());
        orderRepository.save(order);
        requestPayload.setPayment(payment);
        return requestPayload;
    }

    public List<Order> getOrders(){
        return orderRepository.findAll();
    }
}
