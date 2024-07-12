package com.niiad.unbroken.application.outputPorts;

import com.niiad.unbroken.domain.entities.local.LocalProduct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LocalProductOutputPort {
    LocalProduct fetchLocalProductById(Integer productId);

    boolean persistLocalProduct(LocalProduct product);

    List<LocalProduct> fetchAllLocalProducts();

    boolean increaseQuantityInStock(Integer productId, Integer quantity);

    boolean decreaseQuantityInStock(Integer productId, Integer quantity);
}