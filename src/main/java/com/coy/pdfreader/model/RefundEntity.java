package com.coy.pdfreader.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@RequiredArgsConstructor
public class RefundEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String type;
    private Integer quantity;
    private BigDecimal amount;
    private LocalDate data;


    public RefundEntity(String type, Integer quantity, BigDecimal amount, LocalDate data) {
        this.type = type;
        this.quantity = quantity;
        this.amount = amount;
        this.data = data;
    }
}
