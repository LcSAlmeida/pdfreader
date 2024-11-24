package com.coy.pdfreader.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReportEntity {

    private String type;
    private String quantity;
    private String amount;
    private String data;


    public ReportEntity(String type, String quantity, String amount, String data) {
        this.type = type;
        this.quantity = quantity;
        this.amount = amount;
        this.data = data;
    }
}
