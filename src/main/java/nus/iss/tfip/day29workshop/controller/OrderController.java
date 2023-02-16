package nus.iss.tfip.day29workshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nus.iss.tfip.day29workshop.models.Order;
import nus.iss.tfip.day29workshop.repositories.OrderRepo;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class OrderController {
    

    @Autowired
    OrderRepo orderRepo;

    @PostMapping("/order")
    public ResponseEntity<Order> sendOrder(@RequestBody String order) throws IOException {
        Order o = new Order();
        Order orderResult = o.create(order);
        orderRepo.sendOrder(orderResult);
        return new ResponseEntity<>(orderResult, HttpStatus.OK);
    }


    // post http://localhost:8080/api/order
    // requestbody - {name: "", email: "", deliveryDate: "", lineItems: []}
}
