package org.example.rest.models;

import lombok.Data;

import java.util.List;
@Data
public class Purchase {
    private String purchaseId;
    private String customerName;
    private String date;
    private List<Item> items;
    private double totalAmount;
}
