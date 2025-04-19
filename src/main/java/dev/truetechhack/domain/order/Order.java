package dev.truetechhack.domain.order;

import dev.truetechhack.domain.product.Product;
import lombok.Data;

import java.util.Date;
import java.util.List;

//TODO доделать (написать поля)
@Data
public class Order {
    private int id;
    private String fullName;
    private int orderNumber;
    private Date data;
    private List<Product> products;
    private String count;
}
