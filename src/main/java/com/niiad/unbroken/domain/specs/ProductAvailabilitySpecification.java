package com.niiad.unbroken.domain.specs;

import com.niiad.unbroken.domain.entities.local.LocalProduct;

public class ProductAvailabilitySpecification implements Specification<LocalProduct> {

    @Override
    public boolean isSatisfiedBy(LocalProduct product) {
        if (product.getQuantityInStock() == 0) {
            return false;
        } else {
            return true;
        }
    }

}
