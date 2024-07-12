package com.niiad.unbroken.application.inputPorts;

import com.niiad.unbroken.application.outputPorts.LocalProductOutputPort;
import com.niiad.unbroken.application.useCases.LocalProductUseCase;
import com.niiad.unbroken.domain.entities.local.LocalProduct;
import org.springframework.stereotype.Component;

@Component
public class LocalProductInputPort implements LocalProductUseCase {
    private final LocalProductOutputPort localProductOutputPort;

    public LocalProductInputPort(LocalProductOutputPort localProductOutputPort) {
        this.localProductOutputPort = localProductOutputPort;
    }

    @Override
    public LocalProduct createLocalProduct(LocalProduct product) {
        if (persistLocalProduct(product)) {
            return product;
        } else {
            return null;
        }
    }

    @Override
    public LocalProduct deleteLocalProduct(Integer productId) {
        return fetchLocalProduct(productId);
    }

    private boolean persistLocalProduct(LocalProduct product) {
        return localProductOutputPort.persistLocalProduct(product);
    }

    private LocalProduct fetchLocalProduct(Integer productId) {
        return localProductOutputPort.fetchLocalProductById(productId);
    }

}
