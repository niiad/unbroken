package com.niiad.unbroken.application.useCases;

import com.niiad.unbroken.domain.entities.local.LocalProduct;
import org.springframework.stereotype.Component;

@Component
public interface LocalProductUseCase {
    LocalProduct createLocalProduct(LocalProduct product);

    LocalProduct deleteLocalProduct(Integer productId);
}
