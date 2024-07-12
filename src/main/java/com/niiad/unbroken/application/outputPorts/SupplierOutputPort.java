package com.niiad.unbroken.application.outputPorts;

import com.niiad.unbroken.domain.entities.local.Supplier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SupplierOutputPort {
    boolean persistSupplier(Supplier supplier);

    List<Supplier> fetchAllSuppliers();
}