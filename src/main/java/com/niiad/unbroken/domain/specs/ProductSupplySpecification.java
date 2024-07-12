package com.niiad.unbroken.domain.specs;

import com.niiad.unbroken.domain.entities.local.LocalProduct;

public class ProductSupplySpecification implements Specification<LocalProduct> {
    final static public int PRODUCT_MINIMUM_THRESHOLD = 5;

    @Override
    public boolean isSatisfiedBy(LocalProduct product) {
        return product.getQuantityInStock() <= PRODUCT_MINIMUM_THRESHOLD;
    }

}

