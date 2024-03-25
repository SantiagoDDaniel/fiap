package com.fiap.ecommerce.controllers;

import com.fiap.ecommerce.components.OrderDetails;
import com.fiap.ecommerce.entities.Order;
import com.fiap.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public List<OrderDetails> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("")
    public Order createOrder(@RequestBody Order product) {
        return orderService.createOrder(product);
    }

    @PostMapping("/payment-order")
    public ResponseEntity<HttpStatus> paymentOrder(@RequestBody Order product) {
         orderService.deleteOrder(product.getOrderID());
         return (ResponseEntity<HttpStatus>) ResponseEntity.noContent();
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order product) {
        return orderService.updateOrder(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}

