package dev.truetechhack.domain.product;

import lombok.Data;

@Data
public class Product {
    private int id;
    private String name;
    private double price;
    private String size;
    private int count;
    private String location;
}
