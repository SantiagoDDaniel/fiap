package com.fiap.ecommerce.components;

import com.fiap.ecommerce.entities.Order;

public record OrderDetails (Order order, String productName){}
