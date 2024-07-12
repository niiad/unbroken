package com.niiad.unbroken.application.useCases;

import com.niiad.unbroken.domain.entities.local.Supplier;
import org.springframework.stereotype.Component;

@Component
public interface SupplierUseCase {
    Supplier createSupplier(Supplier supplier);
}
