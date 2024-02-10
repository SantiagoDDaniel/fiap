package com.fiap.ecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TB_ITEM")
public class Item {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer amount;
    private BigDecimal price;

    public Item(String name, Integer amount, BigDecimal price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }
}
