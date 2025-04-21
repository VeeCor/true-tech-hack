package dev.truetechhack.domain.supply;

import dev.truetechhack.domain.product.Product;
import lombok.Data;

import java.util.Date;
import java.util.List;

//TODO доделать (написать поля)
@Data
public class Supply <T> {
    private int id;
    private Date createdAt;
    private List<T> suppliers;
    private List<T> products;
    private int quantity;
    private Date outDate;
}
