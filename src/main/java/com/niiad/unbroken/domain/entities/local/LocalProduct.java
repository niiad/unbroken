package com.niiad.unbroken.domain.entities.local;

import com.niiad.unbroken.domain.specs.ProductAvailabilitySpecification;
import com.niiad.unbroken.domain.specs.ProductSupplySpecification;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Predicate;

@Data
@AllArgsConstructor
public class LocalProduct {
    private Integer id;
    private String name;
    private String category;
    private String description;
    private String unitPrice;
    private String sellingPrice;
    private Integer quantityInStock;

    public static Predicate<LocalProduct> getCategory(String category) {
        return product  -> product.getCategory().equals(category);
    }

    public static Predicate<LocalProduct> resupplyStatus() {
        var productSupply = new ProductSupplySpecification();

        return productSupply::isSatisfiedBy;
    }

    public static Predicate<LocalProduct> availabilityStatus() {
        var productAvailability = new ProductAvailabilitySpecification();

        return productAvailability::isSatisfiedBy;
    }

    public LocalProductDisplay toDisplay() {
        return new LocalProductDisplay(id, name);
    }

}
