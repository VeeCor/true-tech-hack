package dev.truetechhack.domain.supply;

import dev.truetechhack.domain.product.Product;
import lombok.Data;

import java.util.Date;
import java.util.List;

//TODO доделать (написать поля)
@Data
public class Supply {
    private int id;
    private String fullName;
    private int supplyNumber;
    private Date data;
    private List<Product> products;
    private String count;
}
