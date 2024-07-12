package com.niiad.unbroken.domain.entities.local;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Inventory {
    private Integer id;
    private String name;
    private String location;
    private String dateCreated;
    private List<LocalProduct> products;

    public Inventory addLocalProduct(LocalProduct product) {
        products.add(product);

        return this;
    }
}
