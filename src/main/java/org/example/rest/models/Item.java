package org.example.rest.models;

import lombok.Data;

@Data
public class Item {
    private String name;
    private int quantity;
    private double price;
}
