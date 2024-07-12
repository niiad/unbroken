package com.niiad.unbroken.application.inputPorts;

import com.niiad.unbroken.application.outputPorts.SupplierOutputPort;
import com.niiad.unbroken.application.useCases.SupplierUseCase;
import com.niiad.unbroken.domain.entities.local.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierInputPort implements SupplierUseCase {
    private final SupplierOutputPort supplierOutputPort;

    public SupplierInputPort(SupplierOutputPort supplierOutputPort) {
        this.supplierOutputPort = supplierOutputPort;
    }

    @Override
    public Supplier createSupplier(Supplier supplier) {
        if (persistSupplier(supplier)) {
            return supplier;
        } else {
            return null;
        }
    }

    private boolean persistSupplier(Supplier supplier) {
        return supplierOutputPort.persistSupplier(supplier);
    }

}