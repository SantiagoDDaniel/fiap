package com.fiap.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "TB_ORDER")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderID;

    private Long customer_id;

    private LocalDate orderDate;

    private String orderStatus;

    private String shippingAddress;

    private String billingAddress;

    private Double totalAmount;

    private String paymentMethod;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;
}
